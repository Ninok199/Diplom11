package com.example.diplom11.Application.Presenters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;

import com.example.diplom11.Application.View.MainActivity;
import com.example.diplom11.Application.View.ChoiceLevelActivity;


/**
 * Презентер для окна с выбором уровня сложности слов
 */
public class ChoiceLevelPresenterImpl implements BasePresenter {
    private AppCompatActivity activity;
    private SharedPreferences mSettings;


    public ChoiceLevelPresenterImpl (AppCompatActivity activity){
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


    /**
     * метод, который сохраняет в настойки приложения уровень слов, который выбрал пользователь
     */
    public void onButtonSave(){
        int key=0;
        SparseBooleanArray a =((ChoiceLevelActivity)activity).menu.getCheckedItemPositions();
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
