package com.example.diplom11.Presenters;

import com.example.diplom11.Data.StatisticData;
import com.example.diplom11.Models.StatisticModel;
import com.example.diplom11.View.StatisticActivity;
import java.util.Calendar;

import java.util.List;


public class StatisticActivityPresenter implements BasePresenter {

    private StatisticActivity activity;
    private StatisticModel model;
    private List<StatisticData> statisticData;
    private final Calendar c = Calendar.getInstance();
    private int mYear = c.get(Calendar.YEAR);
    private int mMonth = c.get(Calendar.MONTH);
    private int mDay = c.get(Calendar.DAY_OF_MONTH);
    private int n;
    public StatisticActivityPresenter(StatisticActivity activity){
        this.activity=activity;
        model =new StatisticModel(activity);
        n = model.getStatisticCountByWord();
        System.out.println(n);


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
    count= model.getCountAnswerWord(i);
    if (count>=3 && (model.getDataAnswerWord(i).equals(mDay + ".0"+(mMonth+1) +"."+ (mYear-2000)))){
        word++;
    }
}
break;
            case 3:
                for (int i=1;i<n;i++){
                    count= model.getCountAnswerWord(i);
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


        statisticData = model.getAllStatistic();
        switch (flag){
            case 1:


                for (int i=1;i<model.getStatisticCount();i++){
                   if (statisticData.get(i).getCorrectAnswer()==1 &&(model.getStatistic(i).getDateAnswer().equals(mDay + ".0"+(mMonth+1) +"."+ (mYear-2000)))){
                       count++;
                   }
                }

                break;
            case 3:


                for (int i=0;i<model.getStatisticCount();i++){
                    if(statisticData.get(i).getCorrectAnswer()==1){
                        count++;
                    }
                }
                break;
        }
       return count;
    }



        public int getIncorrectAnswer(int flag){
        int count =0;
        statisticData = model.getAllStatistic();
        switch (flag){
            case 1:
                for (int i=1;i<model.getStatisticCount();i++){
                    if (statisticData.get(i).getCorrectAnswer()==-1 &&(model.getStatistic(i).getDateAnswer().equals(mDay + ".0"+(mMonth+1) +"."+ (mYear-2000)))){

                        count++;
                    }
                }

                break;
            case 3:
                for (int i=0;i<model.getStatisticCount();i++){
                    if(statisticData.get(i).getCorrectAnswer()==-1){
                        count++;
                    }
                }
                break;
        }
        return count;
    }
}
