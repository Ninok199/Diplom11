package com.example.diplom11.Presenters;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.SparseBooleanArray;

import com.example.diplom11.MainActivity;
import com.example.diplom11.Models.StatisticModel;
import com.example.diplom11.Models.WordModel;
import com.example.diplom11.View.ChoiceLevelActivity;

import java.util.ArrayList;


public class ChoiceLevelPresenterImpl implements BasePresenter {
    private ChoiceLevelActivity activity;
    private WordModel model;
   SharedPreferences mSettings;
    StatisticModel modell;



    public ChoiceLevelPresenterImpl (ChoiceLevelActivity activity){
        this.activity = activity;
        model = new WordModel(activity);
        mSettings = activity.getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE);

        modell = new StatisticModel(activity);
    }

    @Override
    public void onBackClick() {
        activity.finish();
        System.out.println(modell.getStatisticCount());

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
