package com.example.diplom11.Presenters;

import android.app.Activity;

import com.example.diplom11.View.ChoiceModeActivity;

/**
 * Created by Инна on 04.05.2018.
 */

public class ChoiceModePresenterImpl implements BasePresenter {
    private ChoiceModeActivity activity;

    public ChoiceModePresenterImpl(ChoiceModeActivity activity){
        this.activity=activity;
    }
    @Override
    public void onBackClick() {
       activity.finish();
    }

    @Override
    public void onItemCLick(int position) {

    }
}
