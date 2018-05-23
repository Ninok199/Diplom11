package com.example.diplom11.Application.Presenters;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.widget.Toast;
import com.example.diplom11.Application.Database.Entity.StatisticsData;
import com.example.diplom11.Application.Database.Entity.WordData;
import com.example.diplom11.Application.Model.BaseModel;

import com.example.diplom11.Application.Model.StatisticsModel;
import com.example.diplom11.Application.Model.WordModel;

import com.example.diplom11.Application.View.StatisticsActivity;


import java.util.Calendar;

import java.util.List;


public class StatisticsActivityPresenter implements BasePresenter {

    private StatisticsActivity activity;
    private BaseModel<StatisticsData> StatisticsModel;
    private WordModel wordModel;
    private List<StatisticsData> StatisticsData;
    private final Calendar c = Calendar.getInstance();
    private int mYear = c.get(Calendar.YEAR);
    private int mMonth = c.get(Calendar.MONTH);
    private int mDay = c.get(Calendar.DAY_OF_MONTH);
    private int n;
    private int m;


    public StatisticsActivityPresenter(StatisticsActivity activity){
        this.activity=activity;
        StatisticsModel =new StatisticsModel(activity);
        wordModel =new WordModel(activity);
        n =((StatisticsModel)StatisticsModel).getStatisticsCountByWord();
        m =StatisticsModel.getAllCount();
        StatisticsData = StatisticsModel.getAllItems();




    }


    @Override
    public void onBackClick() {

        activity.finish();
    }

    @Override
    public void onItemCLick(int position) {

    }

    public int getStudyWords(int flag){
        int count;
        int word=0;
        switch (flag){
            case 1:
for (int i=1;i<n;i++){
    count= ((StatisticsModel)StatisticsModel).getCountAnswerWord(i);
    if (count>=3 && (((StatisticsModel)StatisticsModel).getDataAnswerWord(i).equals(mDay + ".0"+(mMonth+1) +"."+ (mYear-2000)))){

        word++;
    }
}
break;
            case 3:
                for (int i=1;i<n;i++){
                    count=((StatisticsModel)StatisticsModel).getCountAnswerWord(i);
                    if(count>=3){
                        word++;
                    }
                }
        break;
        }


        return word;
    }


    public int getCorrectAnswer(int flag){
        int count =0;



        switch (flag){
            case 1:


                for (int i=1;i<m;i++){
                   if (StatisticsData.get(i).getCorrectAnswer()==1 &&(StatisticsData.get(i).getDateAnswer().equals(mDay + ".0"+(mMonth+1) +"."+ (mYear-2000)))){
                       count++;
                   }
                }

                break;
            case 3:


                for (int i=0;i<m;i++){
                    if(StatisticsData.get(i).getCorrectAnswer()==1){
                        count++;
                    }
                }
                break;
        }
       return count;
    }


     public void startAgain(){

         AlertDialog.Builder alert = new AlertDialog.Builder(activity);

         alert.setMessage("Вы действительно хотите начать обучение заного?");

         alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int whichButton) {

                for (int i =1;i<m;i++){
                    StatisticsModel.updateItem(new StatisticsData(StatisticsModel.getItem(i).get_id_Statistics(),StatisticsModel.getItem(i).get_id_Statistics(),0,StatisticsModel.getItem(i).getDateAnswer()));
                }

                for (int i=1;i<wordModel.getAllCount();i++){
                    wordModel.updateItem(new WordData(wordModel.getItem(i).get_id(),wordModel.getItem(i).getEnglish(),wordModel.getItem(i).getRussian(),wordModel.getItem(i).getTranscription(),wordModel.getItem(i).getPart_speech(),wordModel.getItem(i).getComplexity(),0));

                }
                 Toast toast =  Toast.makeText(activity,"Статистика очищена,слова успешно добавлены на повтор",Toast.LENGTH_SHORT);
                 toast.show();
             }
         });

         alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int whichButton) {
                 // Canceled.
             }
         });

         alert.show();
     }


        public int getIncorrectAnswer(int flag){
        int count =0;

        switch (flag){
            case 1:


                for (int i=1;i<m;i++){
                    if (StatisticsData.get(i).getCorrectAnswer()==-1 &&(StatisticsData.get(i).getDateAnswer().equals(mDay + ".0"+(mMonth+1) +"."+ (mYear-2000)))){
                        count++;
                    }
                }

                break;
            case 3:
                for (int i=0;i<m;i++){
                    if(StatisticsData.get(i).getCorrectAnswer()==-1){
                        count++;
                    }
                }
                break;
        }
        return count;
    }
}
