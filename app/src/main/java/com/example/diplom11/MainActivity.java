package com.example.diplom11;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.diplom11.View.MainAppActivity;
import com.example.diplom11.View.WordTranslateActivity;


public class MainActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_LEVEL_WORDS = "0";
    public static final String APP_PREFERENCES_STUDY_MODES = "1";
    Intent i;
    String mode;
    SharedPreferences mSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettings = getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE);
        mode = mSettings.getString(APP_PREFERENCES_STUDY_MODES, "0");
        initActivity(mode);

    }

    public void initActivity(String mode){
        switch (mode){
            case "0":
                i = new Intent(this, MainAppActivity.class);
                startActivity(i);
                break;
            case "1":
                i = new Intent(this, WordTranslateActivity.class);
                startActivity(i);

        }
        finish();
    }
    }


