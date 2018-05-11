package com.example.diplom11.Presenters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.diplom11.MainActivity;
import com.example.diplom11.View.ChoiceModeActivity;

/**
 * Created by Инна on 04.05.2018.
 */

public class ChoiceModePresenterImpl implements BasePresenter {
    private ChoiceModeActivity activity;
    SharedPreferences mSettings;
    int pos;


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
