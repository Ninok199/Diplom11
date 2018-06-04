package com.example.diplom11.Application.Presenters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.diplom11.Application.Database.Entity.WordData;
import com.example.diplom11.Application.Model.BaseModel;
import com.example.diplom11.Application.Model.WordModel;
import com.example.diplom11.R;

/**
 * Метод, отвечающий  за логику окна со словарем
 */

public class VocabularyPresenter implements BasePresenter {
    private AppCompatActivity activity;
    private BaseModel<WordData> model;
    private int wordCount;
    int k;
    Toast toast;



    public VocabularyPresenter(AppCompatActivity activity){
        this.activity=activity;
        model =new WordModel(activity);
        wordCount = model.getAllCount();
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
        return model.getItem(i).getEnglish();

    }

    public String initRussItem(int i){
        return model.getItem(i).getRussian();

    }
    public String initTranscriptionItem(int i){
        return model.getItem(i).getTranscription();

    }
    public String initPartSpeechItem(int i){

        int ps= model.getItem(i).getPart_speech();

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
        return "ppppp";
    }

    /**
     * добавление нового слова в базу для дальшейнего обучения
     */
    public void addNewWord(){

        final String POPUP_LOGIN_TITLE="Добавление нового слова";
        final String POPUP_LOGIN_TEXT="Введите пожалуйста";
        final String ENG_HINT="слово на англ";
        final String RUSS_HINT="слово на рус";
        final String TRANSCRIPTION_HINT = "транскрипция";

        final AlertDialog.Builder alert = new AlertDialog.Builder(activity);

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
         spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 k = position+1;
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });

        LinearLayout layout = new LinearLayout(activity.getApplicationContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(eng);
        layout.addView(rus);
        layout.addView(transc);
        layout.addView(spinner);
        alert.setView(layout);


        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        if (checkInputData(eng.getText().toString()) && checkInputData(rus.getText().toString())) {
                            model.addItem(new WordData(eng.getText().toString(), rus.getText().toString(), transc.getText().toString(), k, 3, 0));
                            toast = Toast.makeText(activity, "Запись успешно добавлена", Toast.LENGTH_SHORT);
                            toast.show();
                            activity.finish();

                        } else

                        {
                            toast = Toast.makeText(activity, "Введите и английский и русский перевод, слово сохранено не будет", Toast.LENGTH_SHORT);
                            toast.show();


                        }
                    }
                }

        );


        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

            alert.show();

    }

    boolean checkInputData(String data){
        return !data.equals("");
    }
}
