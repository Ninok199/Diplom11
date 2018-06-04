package com.example.diplom11.Application.Presenters;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.example.diplom11.Application.View.MainActivity;


public class ChoiceModePresenterImpl implements BasePresenter {
    private AppCompatActivity activity;
    private SharedPreferences mSettings;
    private int pos;


    public ChoiceModePresenterImpl(AppCompatActivity activity){
        this.activity=activity;
        mSettings = activity.getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE);

    }
    @Override
    public void onBackClick() {
       activity.finish();
    }

    @Override
    public void onItemCLick(int position) {
        pos=position;


    }

    /**
     * метод сохраняет режим для обучения, который выберет пользователь, в настройки приложения
     */
    public void onButtonSave() {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(MainActivity.APP_PREFERENCES_STUDY_MODES, String.valueOf(pos));
        editor.apply();
        activity.finish();
    }


}
