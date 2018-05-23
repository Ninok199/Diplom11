package com.example.diplom11.Application.Presenters;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.SparseBooleanArray;

import com.example.diplom11.Application.View.MainActivity;
import com.example.diplom11.Application.View.ChoiceLevelActivity;



public class ChoiceLevelPresenterImpl implements BasePresenter {
    private ChoiceLevelActivity activity;
    private SharedPreferences mSettings;


    public ChoiceLevelPresenterImpl (ChoiceLevelActivity activity){
        this.activity = activity;
        mSettings = activity.getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE);

    }

    @Override
    public void onBackClick() {
        activity.finish();


    }

    @Override
    public void onItemCLick(int position) {

        }



    public void onButtonSave(){
        int key=0;
        SparseBooleanArray a =activity.menu.getCheckedItemPositions();
        for(int i=0;i<a.size();i++) {
            key += a.keyAt(i)+2;

        }
            SharedPreferences.Editor editor = mSettings.edit();
            if(key==9) {
                editor.putString(MainActivity.APP_PREFERENCES_LEVEL_WORDS, String.valueOf(0));
            }
            else
                editor.putString(MainActivity.APP_PREFERENCES_LEVEL_WORDS, String.valueOf(key));
            editor.apply();
            activity.finish();
    }


}
