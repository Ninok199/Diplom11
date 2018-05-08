package com.example.diplom11.Models;

import android.content.Context;
import android.database.SQLException;

import com.example.diplom11.Data.DaoMaster;
import com.example.diplom11.Data.DaoSession;
import com.example.diplom11.DataBaseHandlerImpl;
import com.example.diplom11.Data.WordData;

import org.greenrobot.greendao.database.Database;

import java.io.IOException;
import java.util.List;

/**
 * Created by Инна on 05.05.2018.
 */

public class WordModel
{
    //DaoSession daoSession;
    Context context;
    DataBaseHandlerImpl db;
    public WordModel(Context c){
        this.context=c;
       db = new DataBaseHandlerImpl(c);
        initDB();

//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(c,"words"); //The users-db here is the name of our database.
//        Database db = helper.getWritableDb();
//        daoSession = new DaoMaster(db).newSession();

//        db.addWord(new WordData("s1ss","ssss","dddd",1,2,3));
//        db.addWord(new WordData("ss2s","ssss","dddd",1,2,3));
//        db.addWord(new WordData("s3ss","ssss","dddd",1,2,3));
//        db.addWord(new WordData("s4ss","ssss","dddd",1,2,3));
//        db.addWord(new WordData("s5ss","ssss","dddd",1,2,3));
//        db.addWord(new WordData("s6ss","ssss","dddd",1,2,3));
//        db.addWord(new WordData("s7ss","ssss","dddd",1,2,3));
//        db.addWord(new WordData("s8ss","ssss","dddd",1,2,3));
//        db.addWord(new WordData("s9ss","ssss","dddd",1,2,3));
//        db.addWord(new WordData("s10ss","ssss","dddd",1,2,3));
//        for (int i=1;i<20;i++){
//            System.out.println(db.getWord(i).getEnglish());
       // }



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
