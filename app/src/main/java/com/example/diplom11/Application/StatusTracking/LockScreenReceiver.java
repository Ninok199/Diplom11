package com.example.diplom11.Application.StatusTracking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.diplom11.Application.View.MainActivity;

/**
 * Created by Инна on 14.05.2018.
 */

public class LockScreenReceiver extends BroadcastReceiver {

    Intent i;
    public static boolean wasScreenOn = true;

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("start recieve from lockscreenreceiver");
        // если экран выключен то запускаем наш лок скрин
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            wasScreenOn = false;
            System.out.println("openintent from receiver");
            i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

        }
    }
}


