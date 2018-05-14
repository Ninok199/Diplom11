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


    public StatisticData getStatistic(int id) {
        return db.getStatistic(id);
    }

    public List<StatisticData> getAllStatistic() {
        return db.getAllStatistic();
    }


    public int getStatisticCount() {
       return db.getStatisticCount();
    }
    public int getCountAnswerWord(int id){
        return db.getCountAnswerWord(id);
    }
    public void addStatistic(StatisticData statisticData) {
        db.addStatistic(statisticData);
    }

    public String getDataAnswerWord(int id){
        return db.getDataAnswerWord(id);
    }

    public int getStatisticCountByWord() {
        return db.getStatisticCountByWord();
    }
}
