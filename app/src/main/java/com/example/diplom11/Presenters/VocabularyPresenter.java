package com.example.diplom11.Presenters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.diplom11.Data.WordData;
import com.example.diplom11.Models.WordModel;
import com.example.diplom11.R;
import com.example.diplom11.View.VocabularyActivity;



public class VocabularyPresenter implements BasePresenter {
    VocabularyActivity activity;
    WordModel model;
    int wordCount;


    public VocabularyPresenter(VocabularyActivity activity){
        this.activity=activity;
        model =new WordModel(activity);
        wordCount = model.getAllWordsCount();
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

    public void addNewWord(){

        final String POPUP_LOGIN_TITLE="Добавление нового слова";
         final String POPUP_LOGIN_TEXT="Введите пожалуйста";
        final String ENG_HINT="слово на англ";
        final String RUSS_HINT="слово на рус";
        final String TRANSCRIPTION_HINT = "транскрипция";

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);

        alert.setTitle(POPUP_LOGIN_TITLE);
        alert.setMessage(POPUP_LOGIN_TEXT);

        // Set an EditText view to get user input
        final EditText eng = new EditText(activity);
        eng.setHint(ENG_HINT);
        final EditText rus = new EditText(activity);
        rus.setHint(RUSS_HINT);
        final EditText transc = new EditText(activity);
       transc.setHint(TRANSCRIPTION_HINT);

        final Spinner spinner = new Spinner(activity);
        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(activity, R.array.part_speech, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Вызываем адаптер
        spinner.setAdapter(adapter);
        final String selected = spinner.getSelectedItem().toString();
        final long k  = spinner.getSelectedItemId();

        LinearLayout layout = new LinearLayout(activity.getApplicationContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(eng);
        layout.addView(rus);
        layout.addView(transc);
        layout.addView(spinner);
        alert.setView(layout);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                System.out.println(eng.getText().toString()+" "+rus.getText().toString()+" "+k);
                model.addWord(new WordData(eng.getText().toString(),rus.getText().toString(),transc.getText().toString(), (int) k,2,0));
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();
    }
}
