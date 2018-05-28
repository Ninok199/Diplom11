package com.example.diplom11.Application.View;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.diplom11.Application.Presenters.StatisticsActivityPresenter;
import com.example.diplom11.R;


import static com.example.diplom11.R.string.*;
import static com.example.diplom11.R.string.correctanswer;


public class StatisticsActivity extends AppCompatActivity {
StatisticsActivityPresenter presenter;
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
        presenter = new StatisticsActivityPresenter(this);





        

    }



    public void onAllTimeStatisticsClick(View view) {

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

    public void onStartAgainClick(View view) {
        presenter.startAgain();
    }
}
