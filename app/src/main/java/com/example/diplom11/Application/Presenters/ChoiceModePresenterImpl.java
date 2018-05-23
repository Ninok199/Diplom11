package com.example.diplom11.Application.Presenters;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.diplom11.Application.View.MainActivity;
import com.example.diplom11.Application.View.ChoiceModeActivity;


public class ChoiceModePresenterImpl implements BasePresenter {
    private ChoiceModeActivity activity;
    private SharedPreferences mSettings;
    private int pos;


    public ChoiceModePresenterImpl(ChoiceModeActivity activity){
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

    public void onSaveClick(){
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(MainActivity.APP_PREFERENCES_STUDY_MODES, String.valueOf(pos));
        editor.apply();
        activity.finish();
    }
}
