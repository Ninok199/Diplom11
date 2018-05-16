package com.example.diplom11.View;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.diplom11.Presenters.StatisticActivityPresenter;
import com.example.diplom11.R;


import static com.example.diplom11.R.string.*;
import static com.example.diplom11.R.string.correctanswer;


public class StatisticActivity extends AppCompatActivity {
StatisticActivityPresenter presenter;
TextView correctAnswerView;
TextView incorrectAnswer;
TextView studyCount;
TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        correctAnswerView = findViewById(R.id.textViewCorrectAnswer);
        incorrectAnswer = findViewById(R.id.textViewNoCorrectAnswer);
        studyCount = findViewById(R.id.textViewBestCategory);
        text = findViewById(R.id.textView7);
        text.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/11364.ttf"));
        correctAnswerView.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/11364.ttf"));
        incorrectAnswer.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/11364.ttf"));
        studyCount.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/11364.ttf"));
        presenter = new StatisticActivityPresenter(this);





        

    }



    public void onAllTimeStatisticClick(View view) {

        correctAnswerView.setText(getString(correctanswer)+ presenter.getCorrectAnswer(3));
        incorrectAnswer.setText(getString(incorrectanswer)+presenter.getIncorrectAnswer(3));
        studyCount.setText(getString(allwords) +presenter.getStudyWords(3));

    }

    public void OnBackClick(View view) {
        presenter.onBackClick();

    }

    public void onDayStartisticClick(View view) {
        correctAnswerView.setText(getString(correctanswer)+ presenter.getCorrectAnswer(1));
        incorrectAnswer.setText(getString(incorrectanswer)+presenter.getIncorrectAnswer(1));
        studyCount.setText(getString(allwords) +presenter.getStudyWords(1));

    }
}
