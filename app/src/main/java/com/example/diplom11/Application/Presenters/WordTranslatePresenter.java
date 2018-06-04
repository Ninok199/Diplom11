package com.example.diplom11.Application.Presenters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.example.diplom11.Application.Database.Entity.WordData;
import com.example.diplom11.Application.Model.BaseModel;
import com.example.diplom11.Application.View.MainActivity;
import com.example.diplom11.Application.Model.WordModel;
import com.example.diplom11.Application.View.WordTranslateActivity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Метод, который отвечает за логику окна в режиме слово-перевод
 */

public class WordTranslatePresenter implements BasePresenter {

    private AppCompatActivity activity;
    private BaseModel<WordData> model;
    private ArrayList<Integer> complexities;
    private String complex;
    private int positionWord;

    public WordTranslatePresenter(AppCompatActivity activity){
        this.activity=activity;
        model = new WordModel(activity);
        SharedPreferences mSettings = activity.getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE);
        complex = mSettings.getString(MainActivity.APP_PREFERENCES_LEVEL_WORDS,"0");
        positionWord =initPosition();


    }
    public WordTranslatePresenter(WordModel model, int pos){
        this.model =model;
        positionWord = pos;
    }
    @Override
    public void onBackClick() {
        activity.finish();
    }

    @Override
    public void onItemCLick(int position) {

    }


    /**
     * метод, который получает массив идентификаторов слов определенной сложности
     * @param complexity сложность слов
     * @return массив идентификаторов
     */
    private ArrayList<Integer> getComplexityWord(String complexity){
        complexities = new ArrayList<>();
        for(int i = 0; i<((WordModel)model).getWordsCount(complexity); i++){
            int k = (int) ((WordModel)model).getComplexity(complexity).get(i).get_id();
            complexities.add(k);

        }

        return complexities;
    }

    /**
     * метод для генирации одного рандомного слова, который отобразится на экране для обучения
     * @return целое рандомное число
     */
    private int  initPosition(){

        complexities = getComplexityWord(complex);
        Random random = new Random();
        int r = random.nextInt(((WordModel)model).getWordsCount(complex));
          return complexities.get(r);

    }

    public String initEnglishItem(){
            return model.getItem(positionWord).getEnglish();

    }

    public String initRussItem(){
        return model.getItem(positionWord).getRussian();

    }
    public String initTranscriptionItem(){
        return model.getItem(positionWord).getTranscription();

    }
    public String initPartSpeechItem(){

        int ps= model.getItem(positionWord).getPart_speech();

        switch (ps){
            case 1:
                return "noun";

            case 2:
                return "adverb";
            case 3:
                return "verb";
            case 4:
                return "adj";

        }
        return "other";
    }
}
