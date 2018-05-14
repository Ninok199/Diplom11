package com.example.diplom11.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.diplom11.Presenters.StatisticActivityPresenter;
import com.example.diplom11.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;


public class StatisticActivity extends AppCompatActivity {
StatisticActivityPresenter presenter;
TextView correctAnswerView;
TextView incorrectAnswer;
TextView studyCount;
TextView noStudyWordCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        correctAnswerView = findViewById(R.id.textViewCorrectAnswer);
        incorrectAnswer = findViewById(R.id.textViewNoCorrectAnswer);
        studyCount = findViewById(R.id.textViewBestCategory);
        noStudyWordCount = findViewById(R.id.textViewWorstCategory);
        presenter = new StatisticActivityPresenter(this);





        

    }



    public void onAllTimeStatisticClick(View view) {

        correctAnswerView.setText("CorrectAnswer: "+ presenter.getCorrectAnswer(3));
        incorrectAnswer.setText("NO coreect: "+presenter.getIncorrectAnswer(3));
        studyCount.setText("study words: " +presenter.getStudyWords(3));

    }

    public void OnBackClick(View view) {
        presenter.onBackClick();

    }

    public void onDayStartisticClick(View view) {
        correctAnswerView.setText("CorrectAnswer: "+ presenter.getCorrectAnswer(1));
        incorrectAnswer.setText("NO coreect: "+presenter.getIncorrectAnswer(1));
        studyCount.setText("study words: " +presenter.getStudyWords(1));

    }
}
