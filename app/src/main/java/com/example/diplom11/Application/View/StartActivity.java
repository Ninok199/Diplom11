package com.example.diplom11.Application.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.diplom11.Application.StatusTracking.MyService;
import com.example.diplom11.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        // запускаем сервис
        System.out.println("startService from startActivity");
        startService(new Intent(this, MyService.class));

        // убиваем активность
        finish();
    }
}
