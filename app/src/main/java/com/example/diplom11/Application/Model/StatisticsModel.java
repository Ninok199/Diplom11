package com.example.diplom11.Application.Model;

import android.content.Context;

import com.example.diplom11.Application.Database.DatabaseStatisticsService;
import com.example.diplom11.Application.Database.Entity.StatisticsData;
import com.example.diplom11.Application.Database.DataBaseHandlerImpl;
import com.example.diplom11.Application.Model.BaseModel;

import java.util.List;


 public class StatisticsModel implements BaseModel<StatisticsData> {
    private DatabaseStatisticsService db;

    public StatisticsModel(Context c) {

        db = new DatabaseStatisticsService(c);

    }

    public int getCountAnswerWord(int id){
        return db.getCountAnswerWord(id);
    }


    public String getDataAnswerWord(int id){
        return db.getDataAnswerWord(id);
    }

    public int getStatisticsCountByWord() {
        return db.getStatisticsCountByWord();
    }


    @Override
    public void addItem(StatisticsData item) {
        db.addStatistics(item);
    }

    @Override
    public int getAllCount() {
        return db.getStatisticsCount();
    }

    @Override
    public List<StatisticsData> getAllItems() {
        return db.getAllStatistics();
    }

    @Override
    public StatisticsData getItem(int id) {
        return db.getStatistics(id);
    }

    @Override
    public void updateItem(StatisticsData item) {
        db.updateStatistics(item);
    }
}
