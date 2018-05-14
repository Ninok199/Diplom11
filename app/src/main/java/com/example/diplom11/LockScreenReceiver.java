package com.example.diplom11;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Инна on 14.05.2018.
 */

public class LockScreenReceiver extends BroadcastReceiver {

    Intent i;
    public static boolean wasScreenOn = true;

    @Override
    public void onReceive(Context context, Intent intent) {

        // если экран выключен то запускаем наш лок скрин
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            wasScreenOn = false;
            i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}

