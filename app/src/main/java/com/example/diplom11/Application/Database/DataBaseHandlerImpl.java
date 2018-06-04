package com.example.diplom11.Application.Database;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;




public class DataBaseHandlerImpl extends SQLiteOpenHelper  implements IDataBaseHandler {
//word
     final static String _ID = "_id";
    final static String COLUMN_ENGLISH = "english";
    final static String COLUMN_RUSSIAN = "russian";
    final static String COLUMN_TRANSCRIPTION = "transcription";
    final static String COLUMN_PART_SPEECH = "part_speech";
     final static String COLUMN_COMPLEXITY = "complexity";
     final static String COLUMN_WORD_KNOWLEDGE = "word_knowledge";
     final static String TABLE_NAME = "word";

    //Statistics
    final static String TABLE_NAME_StatisticsS = "statistics";
     final static String _ID_Statistics = "_id_statistics";
     final static String _ID_WORD = "_id_word";
     final static String CORRECT_ANSWER = "correct_answer";
     final static String DATE_ANSWER = "date_answer";
    // путь к базе данных вашего приложения
    private static String DB_PATH = "/data/data/com.example.diplom11/databases/";
    private static String DB_NAME = "words.db";
     SQLiteDatabase myDataBase;
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
    @Override
    public void createDataBase() throws IOException{
        boolean dbExist = checkDataBase();
        if(!dbExist){
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
   @Override
   public boolean checkDataBase(){
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
    @Override
    public void copyDataBase() throws IOException{
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
@Override
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
                "\"word_knowledge\" INTEGER NOT NULL );"); // 6: word_category);

        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME_StatisticsS + "(" + _ID_Statistics + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
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





}
