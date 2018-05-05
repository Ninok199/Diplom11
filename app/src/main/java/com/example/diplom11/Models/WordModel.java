package com.example.diplom11.Models;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;

import com.example.diplom11.DataBaseHandlerImpl;
import com.example.diplom11.WordData;

import java.io.IOException;
import java.util.List;

/**
 * Created by Инна on 05.05.2018.
 */

public class WordModel
{
    Context context;
    DataBaseHandlerImpl db;
    public WordModel(Context c){
        this.context=c;
       db = new DataBaseHandlerImpl(c);
    }



    public void initDB(){
        try {
            db.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            db.openDataBase();
        }catch(SQLException sqle){
            throw sqle;
        }
    }
    public void addWord(WordData wordData){
        db.addWord(wordData);

    }

    public WordData getWord(int id) {
        return db.getWord(id);
    }

    public List<WordData> getAllWords() {
        return db.getAllWords();
    }

    public int getWordsCount() {
        return db.getWordsCount();
    }

    public int updateWord(WordData word) {
        return db.updateWord(word);
    }

    public void deleteWord(WordData word) {
        db.deleteWord(word);
    }
}
