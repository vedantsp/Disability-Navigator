package com.example.android.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class First extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }
    public void goto_upload(View view)
    {
        Intent intent = new Intent(First.this,MainActivity.class);
        startActivity(intent);

    }
    public void goto_view(View view)
    {
        Intent intent = new Intent(First.this,ImageListActivity.class);
        startActivity(intent);

    }
    public void goto_specific(View view)
    {
        Intent intent = new Intent(First.this,Main2Activity.class);
        startActivity(intent);

    }
}
