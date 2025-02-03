package com.example.android.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    TextView putvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        putvalue = findViewById(R.id.putvalue);
        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();
        String lon = bundle.getString("value");
        putvalue.setText(lon);
    }
}
