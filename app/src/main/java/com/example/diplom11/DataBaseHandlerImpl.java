package com.example.diplom11;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.diplom11.Data.StatisticData;
import com.example.diplom11.Data.WordData;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;



public class DataBaseHandlerImpl extends SQLiteOpenHelper implements IDataBaseHandler {
//word
    private final static String _ID = "_id";
    private final static String COLUMN_ENGLISH = "english";
    private final static String COLUMN_RUSSIAN = "russian";
    private final static String COLUMN_TRANSCRIPTION = "transcription";
    private final static String COLUMN_PART_SPEECH = "part_speech";
    private final static String COLUMN_COMPLEXITY = "complexity";
    private final static String COLUMN_WORD_CATEGORY = "word_category";
    private final static String TABLE_NAME = "word";

    //statistic
    private final static String TABLE_NAME_STATISTICS = "statistics";
    private final static String _ID_STATISTIC = "_id_statistics";
    private final static String _ID_WORD = "_id_word";
    private final static String CORRECT_ANSWER = "correct_answer";
    private final static String DATE_ANSWER = "date_answer";
    // путь к базе данных вашего приложения
    private static String DB_PATH = "/data/data/com.example.diplom11/databases/";
    private static String DB_NAME = "words.db";
    private SQLiteDatabase myDataBase;
    private Context mContext;

    /**
     * Constructor should be private to prevent direct instantiation.
     * make call to static factory method "getInstance()" instead.
     */



    public DataBaseHandlerImpl(Context context) {
        super(context, DB_NAME, null, 1);
        this.mContext = context;
    }

    /**
     * Создает пустую базу данных и перезаписывает ее нашей собственной базой
     * */
    public void createDataBase() throws IOException{
        boolean dbExist = checkDataBase();

        if(dbExist){
            //ничего не делать - база уже есть
        }else{
            //вызывая этот метод создаем пустую базу, позже она будет перезаписана
            this.getWritableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    /**
     * Проверяет, существует ли уже эта база, чтобы не копировать каждый раз при запуске приложения
     * @return true если существует, false если не существует
     */
    private boolean checkDataBase(){
        boolean checkdb = false;
        try{
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            //checkdb = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
            checkdb = dbfile.exists();
        }
        catch(SQLiteException e){
            System.out.println("Database doesn't exist");
        }

        return checkdb;
    }

    /**
     * Копирует базу из папки assets заместо созданной локальной БД
     * Выполняется путем копирования потока байтов.
     * */
    private void copyDataBase() throws IOException{
        //Открываем локальную БД как входящий поток
        InputStream myInput = mContext.getAssets().open(DB_NAME);

        //Путь ко вновь созданной БД
        String outFileName = DB_PATH + DB_NAME;

        //Открываем пустую базу данных как исходящий поток
        OutputStream myOutput = new FileOutputStream(outFileName);

        //перемещаем байты из входящего файла в исходящий
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //закрываем потоки
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException{
        //открываем БД
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase("/data/data/com.example.diplom11/databases/words.db", null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS"  + "\"word\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT," + // 0: _id
                "\"english\" TEXT," + // 1: english
                "\"russian\" TEXT," + // 2: russian
                "\"transcription\" TEXT," + // 3: transcription
                "\"part_speech\" INTEGER NOT NULL ," + // 4: part_speech
                "\"complexity\" INTEGER NOT NULL ," + // 5: complexity
                "\"word_category\" INTEGER NOT NULL );"); // 6: word_category);

        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME_STATISTICS + "(" + _ID_STATISTIC + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
               _ID_WORD + " INTEGER NOT NULL ," + // 5: complexity
                CORRECT_ANSWER+ " INTEGER NOT NULL, "+
                DATE_ANSWER +" TEXT );"); // 6: word_category););
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        File file = new File(DB_PATH+DB_NAME);
        if (file.exists() && !file.isDirectory())
            file.setWritable(true);
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
        myDataBase = this.getReadableDatabase();
        WordData wordData = null;
        try {


            @SuppressLint("Recycle") Cursor cursor = myDataBase.query(TABLE_NAME, new String[]{_ID, COLUMN_ENGLISH, COLUMN_RUSSIAN, COLUMN_TRANSCRIPTION, COLUMN_PART_SPEECH, COLUMN_COMPLEXITY, COLUMN_WORD_CATEGORY}, COLUMN_ENGLISH + "=?",
                    new String[]{String.valueOf(engword)}, null, null, null, null);

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




    @Override
    public WordData getWord(int id) {
        myDataBase = this.getReadableDatabase();
        WordData data = null;
        Cursor cursor;
        try {

            cursor = myDataBase.query(TABLE_NAME, new String[]{_ID, COLUMN_ENGLISH, COLUMN_RUSSIAN, COLUMN_TRANSCRIPTION, COLUMN_PART_SPEECH, COLUMN_COMPLEXITY, COLUMN_WORD_CATEGORY}, _ID + "=?",
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

    @Override
    public List<WordData> getAllWords() {
        List<WordData> wordList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

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
            word.setWord_category(cursor.getInt(6));

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
                    cursor = myDataBase.query(TABLE_NAME, new String[]{_ID, COLUMN_ENGLISH, COLUMN_RUSSIAN, COLUMN_TRANSCRIPTION, COLUMN_PART_SPEECH, COLUMN_COMPLEXITY, COLUMN_WORD_CATEGORY}, COLUMN_COMPLEXITY + "=?",
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
                            word.setWord_category(cursor.getInt(6));

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
                    cursor = myDataBase.query(TABLE_NAME, new String[]{_ID, COLUMN_ENGLISH, COLUMN_RUSSIAN, COLUMN_TRANSCRIPTION, COLUMN_PART_SPEECH, COLUMN_COMPLEXITY, COLUMN_WORD_CATEGORY}, COLUMN_COMPLEXITY + "=? or " + COLUMN_COMPLEXITY + " =?",
                            new String[]{value1, value2}, null, null, null, null);

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

    @Override
    public StatisticData getStatistic(int id) {
         myDataBase = this.getReadableDatabase();
        Cursor cursor;
        StatisticData data = null;
        try {
            cursor = myDataBase.query(TABLE_NAME_STATISTICS, new String[]{_ID_STATISTIC, _ID_WORD, CORRECT_ANSWER, DATE_ANSWER}, _ID_STATISTIC + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }

            assert cursor != null;
            data = new StatisticData(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3));
        }
        catch (Exception e){
            System.out.println("could not get statistic");
        }
        finally {
            myDataBase.close();
        }
return data;
    }

    @Override
    public List<StatisticData> getAllStatistic() {
        List<StatisticData> wordList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_STATISTICS;
         myDataBase = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = myDataBase.rawQuery(selectQuery, null);
try {

    if (cursor.moveToFirst()) {
        do {
            StatisticData statisticData = new StatisticData();
            statisticData.set_id_statistics(cursor.getInt(0));
            statisticData.set_idWord(cursor.getInt(1));
            statisticData.setCorrectAnswer(cursor.getInt(2));
            statisticData.setDateAnswer(cursor.getString(3));

            wordList.add(statisticData);
        } while (cursor.moveToNext());
    }
}catch (Exception e){
    System.out.println("could not get all statistic");
}finally {
   myDataBase.close();
}
        return wordList;
    }



    @Override
    public int getStatisticCount() {
        myDataBase = this.getReadableDatabase();
        Cursor cursor;
        int count = 0;
          String  countQuery = "SELECT  * FROM " + TABLE_NAME_STATISTICS;
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

    @SuppressLint("Recycle")
    public int getStatisticCountByWord() {
        myDataBase = this.getReadableDatabase();
        Cursor cursor;
        int count = 0;
        String  countQuery = "SELECT  * FROM " + TABLE_NAME_STATISTICS + " group by "+ _ID_WORD;
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

    @SuppressLint("Recycle")
    @Override
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

                    countQuery = "SELECT  * FROM " + TABLE_NAME;
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

                    cursor = myDataBase.query(TABLE_NAME, new String[]{_ID, COLUMN_ENGLISH, COLUMN_RUSSIAN, COLUMN_TRANSCRIPTION, COLUMN_PART_SPEECH, COLUMN_COMPLEXITY, COLUMN_WORD_CATEGORY}, COLUMN_COMPLEXITY + "=?",
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
                    cursor = myDataBase.query(TABLE_NAME, new String[]{_ID, COLUMN_ENGLISH, COLUMN_RUSSIAN, COLUMN_TRANSCRIPTION, COLUMN_PART_SPEECH, COLUMN_COMPLEXITY, COLUMN_WORD_CATEGORY}, COLUMN_COMPLEXITY + "=? or " + COLUMN_COMPLEXITY + "  =?",
                            new String[]{value1, value2}, null, null, null, null);
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

    public void addStatistic(StatisticData statisticData) {
        myDataBase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(_ID_WORD,statisticData.get_idWord());
        values.put(CORRECT_ANSWER, statisticData.getCorrectAnswer());
        values.put(DATE_ANSWER, statisticData.getDateAnswer());

        myDataBase.insert(TABLE_NAME_STATISTICS, null, values);
        myDataBase.close();
    }
    @Override
    public int updateWord(WordData word) {
         myDataBase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ENGLISH, word.getEnglish());
        values.put(COLUMN_RUSSIAN, word.getRussian());
        values.put(COLUMN_TRANSCRIPTION, word.getTranscription());
        values.put(COLUMN_PART_SPEECH, word.getPart_speech());
        values.put(COLUMN_COMPLEXITY, word.getComplexity());
        values.put(COLUMN_WORD_CATEGORY, word.getWord_category());
        return myDataBase.update(TABLE_NAME, values, _ID + " = ?",
                new String[] { String.valueOf(word.get_id()) });
    }

    @Override
    public void deleteWord(WordData word) {
         myDataBase = this.getWritableDatabase();
        myDataBase.delete(TABLE_NAME, _ID + " = ?", new String[] { String.valueOf(word.get_id()) });
       myDataBase.close();
    }

    @Override
    public void deleteAll() {
         myDataBase = this.getWritableDatabase();
        myDataBase.delete(TABLE_NAME, null, null);
        myDataBase.close();
    }
}
