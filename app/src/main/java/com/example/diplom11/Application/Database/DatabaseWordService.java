package com.example.diplom11.Application.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.diplom11.Application.Database.Entity.WordData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Инна on 27.05.2018.
 */

public class DatabaseWordService extends DataBaseHandlerImpl {
    /**
     * Constructor should be private to prevent direct instantiation.
     * make call to static factory method "getInstance()" instead.
     *
     * @param context
     */

    public DatabaseWordService(Context context) {
        super(context);
    }
    @SuppressLint("Recycle")
    public int getWordsCount(String flag) {
        String countQuery;
        myDataBase = this.getReadableDatabase();
        Cursor cursor;
        int count = 0;
        String value1=switchComplexity(flag).get(0);
        String value2=switchComplexity(flag).get(1);
        try {

            switch (flag) {
                case "0":

                    countQuery = "SELECT  * FROM " + TABLE_NAME + " where "+COLUMN_WORD_KNOWLEDGE+ " =0";
                    cursor = myDataBase.rawQuery(countQuery, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                    }
                    assert cursor != null;
                    count = cursor.getCount();
                    break;
                case "2":
                case "3":
                case "4":

                    cursor = myDataBase.query(TABLE_NAME, new String[]{_ID, COLUMN_ENGLISH, COLUMN_RUSSIAN, COLUMN_TRANSCRIPTION, COLUMN_PART_SPEECH, COLUMN_COMPLEXITY, COLUMN_WORD_KNOWLEDGE}, COLUMN_COMPLEXITY + "=? and " + COLUMN_WORD_KNOWLEDGE+ " =0",
                            new String[]{value1}, null, null, null, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                    }
                    assert cursor != null;
                    count = cursor.getCount();

                    break;
                case "5":
                case "6":
                case "7":
                    String query = "select * from " + TABLE_NAME+ " where ( "+ COLUMN_COMPLEXITY+" =" + value1+" or "+ COLUMN_COMPLEXITY+" =" + value2+") and " +COLUMN_WORD_KNOWLEDGE+" = 0;";
                    cursor = myDataBase.rawQuery(query, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                    }
                    assert cursor != null;
                    count = cursor.getCount();
                    break;
            }

        }catch (Exception e){
            System.out.println("could not get word count, sorry!");
        }
        finally {
            myDataBase.close();
        }
        return count;
    }
    public int getAllWordsCount(){
        String countQuery;
        myDataBase = this.getReadableDatabase();
        Cursor cursor;
        int count = 0;
        try {


            countQuery = "SELECT  * FROM " + TABLE_NAME;
            cursor = myDataBase.rawQuery(countQuery, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            assert cursor != null;
            count = cursor.getCount();
        }catch (Exception e){
            System.out.println("");
        }finally {
            myDataBase.close();
        }
        return count;
    }

    public void addWord(WordData word) {
        myDataBase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ENGLISH, word.getEnglish());
        values.put(COLUMN_RUSSIAN, word.getRussian());
        values.put(COLUMN_TRANSCRIPTION, word.getTranscription());
        values.put(COLUMN_PART_SPEECH, word.getPart_speech());
        values.put(COLUMN_COMPLEXITY, word.getComplexity());
        values.put(COLUMN_WORD_KNOWLEDGE, word.getWord_knowledge());
        myDataBase.insert(TABLE_NAME, null, values);
        myDataBase.close();
    }

    public WordData getColumnEnglish(String russ) {
        myDataBase = this.getReadableDatabase();
        WordData wordData = null;
        try {


            @SuppressLint("Recycle") Cursor cursor = myDataBase.query(TABLE_NAME, new String[]{_ID, COLUMN_ENGLISH, COLUMN_RUSSIAN, COLUMN_TRANSCRIPTION, COLUMN_PART_SPEECH, COLUMN_COMPLEXITY, COLUMN_WORD_KNOWLEDGE}, COLUMN_RUSSIAN + "=?",
                    new String[]{String.valueOf(russ)}, null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }

            assert cursor != null;
            wordData = new WordData(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6));
        }
        catch (Exception e){
            System.out.println("could not get english word");
        }
        finally {
            myDataBase.close();
        }
        return wordData;
    }





    public WordData getWord(int id) {
        myDataBase = this.getReadableDatabase();
        WordData data = null;
        Cursor cursor;
        try {

            cursor = myDataBase.query(TABLE_NAME, new String[]{_ID, COLUMN_ENGLISH, COLUMN_RUSSIAN, COLUMN_TRANSCRIPTION, COLUMN_PART_SPEECH, COLUMN_COMPLEXITY, COLUMN_WORD_KNOWLEDGE}, _ID + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }

            assert cursor != null;
            data = new WordData(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6));
        }
        catch (Exception e){
            System.out.println("could not get word by id");
        }
        finally {
            myDataBase.close();
        }
        return data;
    }


    public List<WordData> getAllWords() {
        List<WordData> wordList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " where "+COLUMN_WORD_KNOWLEDGE+" =0";

        myDataBase = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = myDataBase.rawQuery(selectQuery, null);
        try {

            if (cursor.moveToFirst()) {
                do {
                    WordData word = new WordData();
                    word.set_id(cursor.getInt(0));
                    word.setEnglish(cursor.getString(1));
                    word.setRussian(cursor.getString(2));
                    word.setTranscription(cursor.getString(3));
                    word.setPart_speech(cursor.getInt(4));
                    word.setComplexity(cursor.getInt(5));
                    word.setWord_knowledge(cursor.getInt(6));

                    wordList.add(word);
                } while (cursor.moveToNext());
            }
        }
        catch (Exception e){
            System.out.println("could not get all words");
        }
        finally {
            myDataBase.close();
        }
        return wordList;
    }
    public void updateWord(WordData word) {
        myDataBase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ENGLISH, word.getEnglish());
        values.put(COLUMN_RUSSIAN, word.getRussian());
        values.put(COLUMN_TRANSCRIPTION, word.getTranscription());
        values.put(COLUMN_PART_SPEECH, word.getPart_speech());
        values.put(COLUMN_COMPLEXITY, word.getComplexity());
        values.put(COLUMN_WORD_KNOWLEDGE, word.getWord_knowledge());
        myDataBase.update(TABLE_NAME, values, _ID + " = ?",
                new String[]{String.valueOf(word.get_id())});
    }
    private ArrayList<String>switchComplexity(String complexity){
        ArrayList<String> list = new ArrayList<>();
        switch (complexity) {
            case "2":
                list.add(0,"1");
                list.add(1,"0");
                break;
            case "3":
                list.add(0,"2");
                list.add(1,"0");
                break;
            case "4":
                list.add(0,"3");
                list.add(1,"0");
                break;
            case "5":
                list.add(0,"1");
                list.add(1,"2");
                break;
            case "6":
                list.add(0,"1");
                list.add(1,"3");
                break;
            case "7":
                list.add(0,"2");
                list.add(1,"3");
                break;
            default:
                list.add(0,"0");
                list.add(1,"0");
                break;
        }
        return list;
    }

    @SuppressLint("Recycle")
    public List<WordData> getComplexity (String complexity){
        String value1=switchComplexity(complexity).get(0);
        String value2=switchComplexity(complexity).get(1);

        List<WordData> data  = new ArrayList<>();
        myDataBase = this.getReadableDatabase();
        Cursor cursor;
        try {


            switch (complexity) {
                case "2":
                case "3":
                case "4":
                    cursor = myDataBase.query(TABLE_NAME, new String[]{_ID, COLUMN_ENGLISH, COLUMN_RUSSIAN, COLUMN_TRANSCRIPTION, COLUMN_PART_SPEECH, COLUMN_COMPLEXITY, COLUMN_WORD_KNOWLEDGE}, COLUMN_COMPLEXITY + "=? and " + COLUMN_WORD_KNOWLEDGE+ " =0",
                            new String[]{value1}, null, null, null, null);

                    if (cursor.moveToFirst()) {
                        do {
                            WordData word = new WordData();
                            word.set_id(cursor.getInt(0));
                            word.setEnglish(cursor.getString(1));
                            word.setRussian(cursor.getString(2));
                            word.setTranscription(cursor.getString(3));
                            word.setPart_speech(cursor.getInt(4));
                            word.setComplexity(cursor.getInt(5));
                            word.setWord_knowledge(cursor.getInt(6));

                            data.add(word);
                        } while (cursor.moveToNext());
                    }
                    break;
                case "0":
                    data = getAllWords();
                    break;
                case "5":
                case "6":
                case "7":

                    String query = "select * from " + TABLE_NAME+ " where ( "+ COLUMN_COMPLEXITY+" =" + value1+" or "+ COLUMN_COMPLEXITY+" =" + value2+") and " +COLUMN_WORD_KNOWLEDGE+" = 0;";
                    cursor = myDataBase.rawQuery(query, null);
                    if (cursor.moveToFirst()) {
                        do {
                            WordData word = new WordData();
                            word.set_id(cursor.getInt(0));
                            word.setEnglish(cursor.getString(1));
                            word.setRussian(cursor.getString(2));
                            word.setTranscription(cursor.getString(3));
                            word.setPart_speech(cursor.getInt(4));
                            word.setComplexity(cursor.getInt(5));
                            word.setWord_knowledge(cursor.getInt(6));

                            data.add(word);
                        } while (cursor.moveToNext());
                    }

                    break;
            }
        }
        catch (Exception e){
            System.out.println("could not get complexity by id");
        }
        finally {
            myDataBase.close();
        }
        return data;
    }
}
