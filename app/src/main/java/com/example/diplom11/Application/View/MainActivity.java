package com.example.diplom11.Application.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;


import com.example.diplom11.Application.StatusTracking.LockScreenService;


/**
 * Активность, которую запускает сервис,если разблокировали экран
 * в зависимости от настроек,включает соответсвтующее активити
 */
public class MainActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES = "mySettings";
    public static final String APP_PREFERENCES_LEVEL_WORDS = "0";
    public static final String APP_PREFERENCES_STUDY_MODES = "1";
    Intent intent;
    String mode;
    SharedPreferences mSettings;
    PhoneStateListener phoneStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mSettings = getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE);
        mode = mSettings.getString(APP_PREFERENCES_STUDY_MODES, "0");
        startService(new Intent(this, LockScreenService.class));
        // стартуем отслеживание состояния телефона
        phoneStateListener = new PhoneStateListener();
        // узнаем все сервисы которые есть
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        // слушаем когда телефон уходит в сон и включаем нашего блокировщика
        assert telephonyManager != null;
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        initActivity(mode);

    }

    public void initActivity(String mode){
        switch (mode){
            case "0":
                intent = new Intent(this, MainAppActivity.class);
                startActivity(intent);
                break;
            case "1":
                intent= new Intent(this, WordTranslateActivity.class);
                startActivity(intent);

        }
        finish();
    }


}


