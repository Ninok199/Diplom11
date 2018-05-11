package com.example.diplom11.Presenters;

import com.example.diplom11.Models.WordModel;
import com.example.diplom11.View.VocabularyActivity;

/**
 * Created by Инна on 11.05.2018.
 */

public class VocabularyPresenter implements BasePresenter {
    VocabularyActivity activity;
    WordModel model;
    int wordCount;


    public VocabularyPresenter(VocabularyActivity activity){
        this.activity=activity;
        model =new WordModel(activity);
        wordCount = model.getWordsCount("0");
    }
    @Override
    public void onBackClick() {
        activity.finish();

    }

    @Override
    public void onItemCLick(int position) {

    }


    public int getWordCount(){
        return wordCount;

    }
    public String initEnglishItem(int i){
        return model.getWord(i).getEnglish();

    }

    public String initRussItem(int i){
        return model.getWord(i).getRussian();

    }
    public String initTranscriptionItem(int i){
        return model.getWord(i).getTranscription();

    }
    public String initPartSpeechItem(int i){

        int ps= model.getWord(i).getPart_speech();

        switch (ps){
            case 1:
                return "noun";

            case 2:
                return "adj";

        }
        return "ppppp";
    }
}
