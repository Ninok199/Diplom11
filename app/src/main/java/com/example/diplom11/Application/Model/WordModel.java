package com.example.diplom11.Application.Model;

import android.content.Context;

import com.example.diplom11.Application.Database.DataBaseHandlerImpl;
import com.example.diplom11.Application.Database.Entity.WordData;
import com.example.diplom11.Application.Model.BaseModel;

import java.io.IOException;
import java.util.List;

/**
 * Created by Инна on 05.05.2018.
 */

public class WordModel implements BaseModel<WordData>
{

    private DataBaseHandlerImpl db;
    public WordModel(Context c){

        db = new DataBaseHandlerImpl(c);
        initDB();


    }


    private void initDB(){
        try {
            db.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        db.openDataBase();
    }



    public WordData getEnglishWord (String russ){
        return db.getColumnEnglish(russ);
    }


    public int getWordsCount(String flag) {
        return db.getWordsCount(flag);
    }



    public List<WordData> getComplexity(String complexity){
        return db.getComplexity(complexity);
    }





    @Override
    public void addItem(WordData wordData) {
        db.addWord(wordData);
    }

    @Override
    public int getAllCount() {
        return db.getAllWordsCount();
    }

    @Override
    public List<WordData> getAllItems() {
        return db.getAllWords();
    }

    @Override
    public WordData getItem(int id) {
        return db.getWord(id);
    }

    @Override
    public void updateItem(WordData item) {
        db.updateWord(item);
    }
}
