package com.example.diplom11;

import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/**
 * Created by Инна on 14.05.2018.
 */

public class MyService extends Service {

    BroadcastReceiver mReceiver;

    public void setBroadcastReceiver(BroadcastReceiver receiver){
        this.mReceiver = receiver;
    }

    @Override
    public IBinder onBind(Intent arg0) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {



        super.onCreate();


    }

    public int onStartCommand(Intent intent, int flags, int startId) {

        // проверяем в фоне все время работы экрана
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        // фильтруем на появление флага выключения экрана
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        // если это так то запускаем рессивер
        mReceiver = new LockScreenReceiver();
        registerReceiver(mReceiver, filter);
        //return Service.START_STICKY;
        return Service.START_REDELIVER_INTENT;
    }
    @Override
    public void onDestroy() {



        //Disabling service
        stopSelf();
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}
