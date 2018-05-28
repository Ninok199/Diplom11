package com.example.diplom11.Application.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.diplom11.Application.StatusTracking.LockScreenService;

import com.example.diplom11.R;

/**
 * Стартовое активити программы, отсюда происходит запуск сервиса для отслежки
 * состояния телефона
 */
public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        // запускаем сервис
        startService(new Intent(this, LockScreenService.class));
        // убиваем активность
        finish();
    }
}
