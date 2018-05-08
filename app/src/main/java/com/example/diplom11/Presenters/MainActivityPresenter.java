package com.example.diplom11.Presenters;

import android.content.Context;

import com.example.diplom11.MainActivity;
import com.example.diplom11.Models.WordModel;
import com.example.diplom11.View.MainAppActivity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Created by Инна on 04.05.2018.
 */

public class MainActivityPresenter implements BasePresenter {
    Context context;
    MainAppActivity activity;
    WordModel model;
    ArrayList<Integer> integers;
    Random random;

    public MainActivityPresenter(MainAppActivity activity){
        this.activity=activity;

    }

    public MainActivityPresenter(Context c){
     this.context=c;
     model = new WordModel(c);
    }

    @Override
    public void onBackClick() {

    }

    @Override
    public void onItemCLick(int position) {

        activity.mSelectText.setText(" " + activity.mAdapter.getItem(position));
        finish(position);

    }

    public void finish(int position){
        if(position==2){
            activity.finish();
        }
    }

    public ArrayList<Integer> initPosition(){
        int count =0;
        integers = new ArrayList<>();
        random=new Random();
        while (count!=4) {
            int r = random.nextInt(10) + 1;
            integers.add(r);
            count++;
            if (count >= 2) {
                for (int i = 0; i < integers.size(); i++) {
                    for (int j = i + 1; j < integers.size(); j++) {
                        if (Objects.equals(integers.get(i), integers.get(j))) {
                            integers.remove(i);
                            count--;
                        }

                    }
                }
            }
        }
        return integers;
    }

}
