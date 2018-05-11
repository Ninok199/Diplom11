package com.example.diplom11.Models;

import android.content.Context;

import com.example.diplom11.Data.StatisticData;
import com.example.diplom11.Data.WordData;
import com.example.diplom11.DataBaseHandlerImpl;

import java.util.List;

/**
 * Created by Инна on 11.05.2018.
 */

public class StatisticModel {
    private Context context;
    private DataBaseHandlerImpl db;

    public StatisticModel(Context c) {

        this.context = c;
        db = new DataBaseHandlerImpl(c);

    }


    StatisticData getStatistic(int id) {
        return db.getStatistic(id);
    }

    List<StatisticData> getAllStatistic() {
        return db.getAllStatistic();
    }

    List<StatisticData> getStatisticData(String date) {
        return db.getStatisticData(date);
    }

    public int getStatisticCount() {
       return db.getStatisticCount();
    }

}
