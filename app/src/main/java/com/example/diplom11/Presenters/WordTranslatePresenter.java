package com.example.diplom11.Presenters;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.diplom11.MainActivity;
import com.example.diplom11.Models.WordModel;
import com.example.diplom11.View.WordTranslateActivity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Created by Инна on 11.05.2018.
 */

public class WordTranslatePresenter implements BasePresenter {

    WordTranslateActivity activity;
    WordModel model;
    private ArrayList<Integer> complexities;
    private Random random;
    private SharedPreferences mSettings;
    String complex;
    int positionWord;

    public WordTranslatePresenter(WordTranslateActivity activity){
        this.activity=activity;
        model = new WordModel(activity);
        mSettings = activity.getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE);
        complex = mSettings.getString(MainActivity.APP_PREFERENCES_LEVEL_WORDS,"0");
        positionWord =initPosition();


    }
    @Override
    public void onBackClick() {
        activity.finish();
    }

    @Override
    public void onItemCLick(int position) {

    }

    public ArrayList<Integer> getComplexityWord(String complexity){
        complexities = new ArrayList<>();
        for(int i = 0; i<model.getWordsCount(complexity); i++){
            int k = (int) model.getComplexity(complexity).get(i).get_id();
            complexities.add(k);


        }

        return complexities;
    }

    public int  initPosition(){

        complexities = getComplexityWord(complex);
        random=new Random();
        int r = random.nextInt(model.getWordsCount(complex));
          return complexities.get(r);

    }

    public String initEnglishItem(){
            return model.getWord(positionWord).getEnglish();

    }

    public String initRussItem(){
        return model.getWord(positionWord).getRussian();

    }
    public String initTranscriptionItem(){
        return model.getWord(positionWord).getTranscription();

    }
    public String initPartSpeechItem(){

        int ps= model.getWord(positionWord).getPart_speech();

        switch (ps){
            case 1:
                return "noun";

            case 2:
                return "adj";

        }
        return "ppppp";
    }
}
