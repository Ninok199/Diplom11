package com.example.diplom11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickConfigMenu(View view) {
        intent = new Intent(MainActivity.this, com.example.diplom11.View.ConfigurationActivity.class);
        startActivity(intent);
        finish();


    }
}
