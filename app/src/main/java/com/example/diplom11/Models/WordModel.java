package com.example.diplom11.Models;

import android.content.Context;
import android.database.SQLException;

import com.example.diplom11.Data.DaoSession;
import com.example.diplom11.DataBaseHandlerImpl;
import com.example.diplom11.Data.WordData;
import com.example.diplom11.MyApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Инна on 05.05.2018.
 */

public class WordModel
{

    private Context context;
    private DataBaseHandlerImpl db;
    public WordModel(Context c){

        this.context=c;
        db = new DataBaseHandlerImpl(c);
        initDB();


//        db.addWord(new WordData("s11ss","sss11s","dddd",1,2,3));
//        db.addWord(new WordData("ss12s","ss12ss","dddd",1,2,3));
//        db.addWord(new WordData("s13ss","s13sss","dddd",1,2,3));
//        db.addWord(new WordData("s14ss","sss14s","dddd",1,2,3));
//        db.addWord(new WordData("s15ss","s15sss","dddd",1,2,3));
//        db.addWord(new WordData("s16ss","ss16ss","dddd",1,2,3));
//        db.addWord(new WordData("s17ss","s17sss","dddd",1,2,3));
//        db.addWord(new WordData("s18ss","s18sss","dddd",1,2,3));
//        db.addWord(new WordData("s19ss","ss19ss","dddd",1,2,3));
//        db.addWord(new WordData("s20ss","s20sss","dddd",1,2,3));



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

    public WordData getRussWord (String eng){
        return db.getColumnRussian(eng);
    }
    public WordData getWord(int id) {
        return db.getWord(id);
    }

    public List<WordData> getAllWords() {
        return db.getAllWords();
    }

    public int getWordsCount(String flag) {
        return db.getWordsCount(flag);
    }

    public int updateWord(WordData word) {
        return db.updateWord(word);
    }

    public void deleteWord(WordData word) {
        db.deleteWord(word);
    }

    public List<WordData> getComplexity(String complexity){
        return db.getComplexity(complexity);
    }
}
