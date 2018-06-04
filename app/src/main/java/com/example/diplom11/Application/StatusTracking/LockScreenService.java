package com.example.diplom11.Application.StatusTracking;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;


/**
 * Сервис для отслеживания состояния телефона
 */

public class LockScreenService extends Service {

   private BroadcastReceiver mReceiver;



    @Override
    public IBinder onBind(Intent arg0) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * метод для начала работы сервиса
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    public int onStartCommand(Intent intent, int flags, int startId) {
        // проверяем в фоне все время работы экрана
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        // фильтруем на появление флага выключения экрана
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        // если это так то запускаем рессивер
        mReceiver = new LockScreenReceiver();
        registerReceiver(mReceiver, filter);
        return Service.START_REDELIVER_INTENT;
    }
    @Override
    public void onDestroy() {
        stopSelf();
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}
