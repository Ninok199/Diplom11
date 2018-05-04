package com.example.diplom11.Presenters;

import com.example.diplom11.View.ChoiceLevelActivity;
import com.example.diplom11.View.ConfigurationActivity;



public class ChoiceLevelPresenterImpl implements BasePresenter {
    private ChoiceLevelActivity activity;


    public ChoiceLevelPresenterImpl (ChoiceLevelActivity activity){
        this.activity = activity;

    }

    @Override
    public void onBackClick() {
        activity.finish();

    }

    @Override
    public void onItemCLick(int position) {

    }

    public void onButtonSave(){

    }
}
