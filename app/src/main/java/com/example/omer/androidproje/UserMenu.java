package com.example.omer.androidproje;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserMenu extends AppCompatActivity {

    TextView tVUM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        tVUM = (TextView) findViewById(R.id.tVUM);

        String json = this.getIntent().getStringExtra("Giris Json");

        tVUM.setText(json);

    }


}
