package com.example.diplom11.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.diplom11.Presenters.WordTranslatePresenter;
import com.example.diplom11.R;

/**
 * Created by Инна on 11.05.2018.
 */

public class WordTranslateActivity extends AppCompatActivity {

    WordTranslatePresenter presenter;
    Intent intent;
    TextView engText;
    TextView rusText;
    TextView transText;
    TextView partSpeechText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_word_translation);
        presenter = new WordTranslatePresenter(this);
        initItems();

    }

    public void onBackClick(View view) {
        presenter.onBackClick();
    }

    public void onClickConfigMenu(View view) {

            intent = new Intent(WordTranslateActivity.this, com.example.diplom11.View.ConfigurationActivity.class);
            startActivity(intent);
            finish();


    }

    public void initItems(){
        engText = findViewById(R.id.textViewEng);
        rusText = findViewById(R.id.textViewRuss);
        transText = findViewById(R.id.textViewTransc);
        partSpeechText = findViewById(R.id.textViewPartSpeech);

        engText.setText(presenter.initEnglishItem());
        rusText.setText(presenter.initRussItem());
        transText.setText(presenter.initTranscriptionItem());
        partSpeechText.setText(presenter.initPartSpeechItem());
    }
}
