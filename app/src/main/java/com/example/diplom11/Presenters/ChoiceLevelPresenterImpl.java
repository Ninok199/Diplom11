package com.example.diplom11.Presenters;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.SparseBooleanArray;

import com.example.diplom11.MainActivity;
import com.example.diplom11.Models.WordModel;
import com.example.diplom11.View.ChoiceLevelActivity;

import java.util.ArrayList;


public class ChoiceLevelPresenterImpl implements BasePresenter {
    private ChoiceLevelActivity activity;
    private WordModel model;
   SharedPreferences mSettings;



    public ChoiceLevelPresenterImpl (ChoiceLevelActivity activity){
        this.activity = activity;
        model = new WordModel(activity);
        mSettings = activity.getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE);

    }

    @Override
    public void onBackClick() {
        activity.finish();

    }

    @Override
    public void onItemCLick(int position) {
        if (position == 0 || position == 1 || position == 2) {
            System.out.println(position);

        }
    }


    public void onButtonSave(){
        SparseBooleanArray a =activity.menu.getCheckedItemPositions();
        for(int i=0;i<a.size();i++){
            int key = a.keyAt(i);
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString(MainActivity.APP_PREFERENCES_LEVEL_WORDS, String.valueOf(key+1));
            editor.apply();

        }

    }


}
