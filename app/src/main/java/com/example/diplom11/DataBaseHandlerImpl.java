package com.example.diplom11;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


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
    // путь к базе данных вашего приложения
    private static String DB_PATH = "/data/data/com.example.diplom11/databases/";
    private static String DB_NAME = "words.db";
    private SQLiteDatabase myDataBase;
    private final Context mContext;

    /**
     * Конструктор
     * Принимает и сохраняет ссылку на переданный контекст для доступа к ресурсам приложения
     * @param context
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
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
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
        Cursor cursor;
                cursor = myDataBase.query(TABLE_NAME, new String[]{_ID, COLUMN_ENGLISH, COLUMN_RUSSIAN, COLUMN_TRANSCRIPTION, COLUMN_PART_SPEECH, COLUMN_COMPLEXITY, COLUMN_WORD_CATEGORY}, _ID + "=?",
                        new String[]{String.valueOf(id)}, null, null, null, null);

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

    private ArrayList<String>switchComplexity(String complexity){
        ArrayList<String> list = new ArrayList<>();
        System.out.println(complexity);
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
        System.out.println(value1 + "  "+value2+"gC");


        List<WordData> data  = new ArrayList<>();
        SQLiteDatabase myDataBase = this.getReadableDatabase();
        Cursor cursor;
        if(complexity.equals("2")||complexity.equals("3")||complexity.equals("4")) {
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
        }
        else if(complexity.equals("0")){
            data=getAllWords();
        }

        else if(complexity.equals("5")||complexity.equals("6")||complexity.equals("7")){
            cursor = myDataBase.query(TABLE_NAME, new String[]{_ID, COLUMN_ENGLISH, COLUMN_RUSSIAN, COLUMN_TRANSCRIPTION, COLUMN_PART_SPEECH, COLUMN_COMPLEXITY, COLUMN_WORD_CATEGORY}, COLUMN_COMPLEXITY + "=? or "+ COLUMN_COMPLEXITY+" =?",
                    new String[]{value1,value2}, null, null, null, null);

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

        }
        return data;
    }
    @SuppressLint("Recycle")
    @Override
    public int getWordsCount(String flag) {
        String countQuery = null;
        myDataBase = this.getReadableDatabase();
        Cursor cursor;
        int count = 0;
        String value1=switchComplexity(flag).get(0);
        String value2=switchComplexity(flag).get(1);
        System.out.println(value1 + "  "+value2+"wc");
        if(flag.equals("0")) {

            countQuery = "SELECT  * FROM " + TABLE_NAME;
            cursor = myDataBase.rawQuery(countQuery, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            assert cursor != null;
            count = cursor.getCount();
        }
        else if(flag.equals("2")||flag.equals("3")||flag.equals("4")){

                cursor = myDataBase.query(TABLE_NAME, new String[]{_ID, COLUMN_ENGLISH, COLUMN_RUSSIAN, COLUMN_TRANSCRIPTION, COLUMN_PART_SPEECH, COLUMN_COMPLEXITY, COLUMN_WORD_CATEGORY}, COLUMN_COMPLEXITY + "=?",
                        new String[]{value1}, null, null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                }
                assert cursor != null;
                count = cursor.getCount();

        }
        else if(flag.equals("5")||flag.equals("6")||flag.equals("7")){
            cursor = myDataBase.query(TABLE_NAME, new String[]{_ID, COLUMN_ENGLISH, COLUMN_RUSSIAN, COLUMN_TRANSCRIPTION, COLUMN_PART_SPEECH, COLUMN_COMPLEXITY, COLUMN_WORD_CATEGORY}, COLUMN_COMPLEXITY + "=? or "+ COLUMN_COMPLEXITY+ "  =?",
                    new String[]{value1,value2}, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            assert cursor != null;
            count = cursor.getCount();
        }


                return count;
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
