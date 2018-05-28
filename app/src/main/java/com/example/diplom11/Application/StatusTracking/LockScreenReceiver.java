package com.example.diplom11.Application.StatusTracking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.diplom11.Application.View.MainActivity;

import java.util.Objects;

/**
 * Приемник широковещательных сообщений для включения главного экрана,если придет соответсвующий флаг
 */

public class LockScreenReceiver extends BroadcastReceiver {


    public static boolean wasScreenOn = true;

    @Override
    public void onReceive(Context context, Intent intent) {
        // если экран выключен то запускаем наш лок скрин
        if (Objects.equals(intent.getAction(), Intent.ACTION_SCREEN_OFF)) {
            wasScreenOn = false;
            Intent in = new Intent(context, MainActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(in);

        }
    }
}


