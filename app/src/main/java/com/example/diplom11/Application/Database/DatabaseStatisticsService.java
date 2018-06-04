package com.example.diplom11.Application.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import com.example.diplom11.Application.Database.Entity.StatisticsData;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, который отвечает за запросы в таблицу Statistics
 */

public class DatabaseStatisticsService extends DataBaseHandlerImpl {
    /**
     * Constructor should be private to prevent direct instantiation.
     * make call to static factory method "getInstance()" instead.
     *
     * @param context контекст ативити
     */

    public DatabaseStatisticsService(Context context) {
        super(context);
    }

    /**
     * метод для нахождения суммы ответов по каждому слову
     * @param id идентификатор
     * @return count
     */
    public int getCountAnswerWord(int id){
        myDataBase = this.getReadableDatabase();
        String selectQuery = "select statistics._id_word, sum(statistics.correct_answer)  from word, statistics where word._id = statistics._id_word and word._id ="+ id+ " group by word._id;";
        @SuppressLint("Recycle") Cursor cursor = myDataBase.rawQuery(selectQuery, null);
        int count=0;
        try {
            if (cursor != null) {
                cursor.moveToFirst();
            }

            assert cursor != null;
            count =  cursor.getInt(1);
        }
        catch (Exception e){
            System.out.println("could not get count answer word");
        }finally {
            myDataBase.close();
        }

        return count;

    }

    /**
     * метод для получения даты последнего ответа на поставленный вопрос
     * @param id идетификатор слова
     * @return дату последнего ответа
     */
    public String getDataAnswerWord(int id){
        myDataBase = this.getReadableDatabase();
        String selectQuery = "select statistics.date_answer, sum(statistics.correct_answer)  from word, statistics where word._id = statistics._id_word and word._id ="+ id+ " group by word._id;";
        @SuppressLint("Recycle") Cursor cursor = myDataBase.rawQuery(selectQuery, null);
        String data="";
        try {


            if (cursor != null) {
                cursor.moveToFirst();
            }

            assert cursor != null;
            data = cursor.getString(0);
        }
        catch (Exception e){
            System.out.println("could not getDataAnswerWord");
        }finally {
            myDataBase.close();
        }
        return data;

    }

    /**
     * метод для получения одного поля со статистикой из базы
     * @param id идентификатор слова
     * @return поле статистики
     */
    @SuppressLint("Recycle")
    public StatisticsData getStatistics(int id) {
        myDataBase = this.getReadableDatabase();
        Cursor cursor;
        StatisticsData data = null;
        try {
            cursor = myDataBase.query(TABLE_NAME_StatisticsS, new String[]{_ID_Statistics, _ID_WORD, CORRECT_ANSWER, DATE_ANSWER}, _ID_Statistics + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }

            assert cursor != null;
            data = new StatisticsData(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3));
        }
        catch (Exception e){
            System.out.println("could not get Statistics");
        }
        finally {
            myDataBase.close();
        }
        return data;
    }

    /**
     * метод для получения всех записей с базы статистики
     * @return всю статистику
     */
    public List<StatisticsData> getAllStatistics() {
        List<StatisticsData> wordList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_StatisticsS;
        myDataBase = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = myDataBase.rawQuery(selectQuery, null);
        try {

            if (cursor.moveToFirst()) {
                do {
                    StatisticsData StatisticsData = new StatisticsData();
                    StatisticsData.set_id_Statistics(cursor.getInt(0));
                    StatisticsData.set_idWord(cursor.getInt(1));
                    StatisticsData.setCorrectAnswer(cursor.getInt(2));
                    StatisticsData.setDateAnswer(cursor.getString(3));

                    wordList.add(StatisticsData);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            System.out.println("could not get all Statistics");
        }finally {
            myDataBase.close();
        }
        return wordList;
    }


    /**
     * метод для подсчета количетва полей статистики в базе
     * @return количество полей
     */
    @SuppressLint("Recycle")
    public int getStatisticsCount() {
        myDataBase = this.getReadableDatabase();
        Cursor cursor;
        int count = 0;
        String  countQuery = "SELECT  * FROM " + TABLE_NAME_StatisticsS;
        try {

            cursor = myDataBase.rawQuery(countQuery, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            assert cursor != null;
            count = cursor.getCount();
        }catch (Exception e){
            System.out.println("");
        }
        finally {
            myDataBase.close();
        }
        return count;
    }

    /**
     * метод для подсчета количества слов в базе статистики
     * @return количество слов в базе
     */
    @SuppressLint("Recycle")
    public int getStatisticsCountByWord() {
        myDataBase = this.getReadableDatabase();
        Cursor cursor;
        int count = 0;
        String  countQuery = "SELECT  * FROM " + TABLE_NAME_StatisticsS + " group by "+ _ID_WORD;
        try {

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


    /**
     * метод для добавления нового поля в статистику
     * @param StatisticsData новое слово
     */
    public void addStatistics(StatisticsData StatisticsData) {
        myDataBase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(_ID_WORD,StatisticsData.get_idWord());
        values.put(CORRECT_ANSWER, StatisticsData.getCorrectAnswer());
        values.put(DATE_ANSWER, StatisticsData.getDateAnswer());

        myDataBase.insert(TABLE_NAME_StatisticsS, null, values);
        myDataBase.close();
    }


    /**
     * метод для обновления статистики
     * @param statisticsData слово, которое надо обновить
     */
    public void updateStatistics(StatisticsData statisticsData){
        myDataBase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(_ID_Statistics, statisticsData.get_id_Statistics());
        values.put(_ID_WORD, statisticsData.get_idWord());
        values.put(CORRECT_ANSWER,statisticsData.getCorrectAnswer());
        values.put(DATE_ANSWER,statisticsData.getDateAnswer());
        myDataBase.update(TABLE_NAME_StatisticsS, values, _ID_Statistics + " = ?", new String[]{String.valueOf(statisticsData.get_id_Statistics())});
    }

}
