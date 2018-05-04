package com.example.diplom11.Presenters;

import com.example.diplom11.MainActivity;

/**
 * Created by Инна on 04.05.2018.
 */

public class MainActivityPresenter implements BasePresenter {
    MainActivity activity;

    public MainActivityPresenter(MainActivity activity){
        this.activity=activity;
    }
    @Override
    public void onBackClick() {

    }

    @Override
    public void onItemCLick(int position) {
        activity.mSelectText.setText("Выбранный элемент: " + activity.mAdapter.getItem(position));

    }
}
