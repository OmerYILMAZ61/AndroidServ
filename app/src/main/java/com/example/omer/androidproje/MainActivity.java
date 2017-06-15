package com.example.omer.androidproje;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText eTMAAdi;
    EditText eTMASif;
    TextView tVMASon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void giris(View v ){

        String url="http://192.168.0.12:8082/AndroidService/rest/android/kontrol/";
        eTMAAdi = (EditText) findViewById(R.id.eTMAAdi);
        eTMASif = (EditText) findViewById(R.id.eTMASif);

        String veri = url+eTMAAdi.getText().toString()+"/"+eTMASif.getText().toString();

        Servis servis = new Servis();

        servis.execute(veri);

    }

    private class Servis extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            String urlveri = params[0];
            try {
                URL url = new URL(urlveri);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String satir;

                    while ((satir = buff.readLine())!=null){
                        sb.append(satir);
                    }

                    return sb.toString();
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "aq";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tVMASon = (TextView) findViewById(R.id.tVMASon);
            tVMASon.setText(s);
            Intent intIntent = new Intent(MainActivity.this,UserMenu.class);
            intIntent.putExtra("Giris Json",s);
            startActivity(intIntent);
        }



    }
}
