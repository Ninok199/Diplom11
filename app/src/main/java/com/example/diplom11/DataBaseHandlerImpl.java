package com.example.diplom11;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.diplom11.Data.WordData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;



public class DataBaseHandlerImpl extends SQLiteOpenHelper implements IDataBaseHandler {

    private final static String _ID = "_id";
    private final static String COLUMN_ENGLISH = "english";
    private final static String COLUMN_RUSSIAN = "russian";
    private final static String COLUMN_TRANSCRIPTION = "transcription";
    private final static String COLUMN_PART_SPEECH = "part_speech";
    private final static String COLUMN_COMPLEXITY = "complexity";
    private final static String COLUMN_WORD_CATEGORY = "word_category";
    private final static String TABLE_NAME = "word";
    private String DB_PATH = null;
    private static String DB_NAME = "words.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;


    @SuppressLint("SdCardPath")
    public DataBaseHandlerImpl(Context context) {
        super(context, DB_NAME, null, 10);
        this.myContext = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
        Log.e("Path 1", DB_PATH);
    }



    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        File dbFile = myContext.getDatabasePath(DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);



    }



    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void addWord(WordData word) {
        myDataBase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ENGLISH, word.getEnglish());
        values.put(COLUMN_RUSSIAN, word.getRussian());
        values.put(COLUMN_TRANSCRIPTION, word.getTranscription());
        values.put(COLUMN_PART_SPEECH, word.getPart_speech());
        values.put(COLUMN_COMPLEXITY, word.getComplexity());
        values.put(COLUMN_WORD_CATEGORY, word.getWord_category());
        myDataBase.insert(TABLE_NAME, null, values);
        myDataBase.close();
    }

    public WordData getColumnRussian(String engword) {
        SQLiteDatabase myDataBase = this.getReadableDatabase();

        @SuppressLint("Recycle") Cursor cursor = myDataBase.query(TABLE_NAME, new String[] { _ID,COLUMN_ENGLISH,COLUMN_RUSSIAN,COLUMN_TRANSCRIPTION,COLUMN_PART_SPEECH,COLUMN_COMPLEXITY,COLUMN_WORD_CATEGORY }, COLUMN_ENGLISH + "=?",
                new String[] { String.valueOf(engword) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        assert cursor != null;

        return new WordData(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6));
    }
    @Override
    public WordData getWord(int id) {
        SQLiteDatabase myDataBase = this.getReadableDatabase();

        @SuppressLint("Recycle") Cursor cursor = myDataBase.query(TABLE_NAME, new String[] { _ID,COLUMN_ENGLISH,COLUMN_RUSSIAN,COLUMN_TRANSCRIPTION,COLUMN_PART_SPEECH,COLUMN_COMPLEXITY,COLUMN_WORD_CATEGORY }, _ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        assert cursor != null;

        return new WordData(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6));
    }

    @Override
    public List<WordData> getAllWords() {
        List<WordData> wordList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                WordData word = new WordData();
                word.set_id(cursor.getInt(0));
                word.setEnglish(cursor.getString(1));
                word.setRussian(cursor.getString(2));
                word.setTranscription(cursor.getString(3));
                word.setPart_speech(cursor.getInt(4));
                word.setComplexity(cursor.getInt(5));
                word.setWord_category(cursor.getInt(6));

                wordList.add(word);
            } while (cursor.moveToNext());
        }

        return wordList;
    }

    @Override
    public int getWordsCount() {

        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    @Override
    public int updateWord(WordData word) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ENGLISH, word.getEnglish());
        values.put(COLUMN_RUSSIAN, word.getRussian());
        values.put(COLUMN_TRANSCRIPTION, word.getTranscription());
        values.put(COLUMN_PART_SPEECH, word.getPart_speech());
        values.put(COLUMN_COMPLEXITY, word.getComplexity());
        values.put(COLUMN_WORD_CATEGORY, word.getWord_category());
        return db.update(TABLE_NAME, values, _ID + " = ?",
                new String[] { String.valueOf(word.get_id()) });
    }

    @Override
    public void deleteWord(WordData word) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, _ID + " = ?", new String[] { String.valueOf(word.get_id()) });
        db.close();
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
}
