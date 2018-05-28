package com.example.diplom11.Application.Presenters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.example.diplom11.Application.Database.Entity.StatisticsData;
import com.example.diplom11.Application.Database.Entity.WordData;
import com.example.diplom11.Application.View.MainActivity;
import com.example.diplom11.Application.Model.BaseModel;
import com.example.diplom11.Application.Model.StatisticsModel;
import com.example.diplom11.Application.Model.WordModel;
import com.example.diplom11.Application.View.MainAppActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.Random;



public class MainActivityPresenter implements BasePresenter {

    private AppCompatActivity activity;
    private BaseModel<WordData> wordModel;
    private BaseModel<StatisticsData> StatisticsModel;
    private ArrayList<Integer> integers;
    private ArrayList<Integer> complexities;
    private Random random;
    private String complex;
     private int StatisticsCountByWord =0;


    public MainActivityPresenter(AppCompatActivity activity){
        this.activity=activity;
        wordModel = new WordModel(activity.getApplicationContext());
        StatisticsModel = new StatisticsModel(activity.getApplicationContext());
        SharedPreferences mSettings = activity.getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE);
        complex = mSettings.getString(MainActivity.APP_PREFERENCES_LEVEL_WORDS,"0");
        StatisticsCountByWord= ((StatisticsModel)StatisticsModel).getStatisticsCountByWord();
        

        checkWords();

    }


    @Override
    public void onBackClick() {

    }

    @Override
    public void onItemCLick(int position) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        if(Objects.equals(((MainAppActivity)activity).getmSelectText().getText().toString(), ((WordModel)wordModel).getEnglishWord(((MainAppActivity)activity).mAdapter.getItem(position)).getEnglish())){
            StatisticsModel.addItem(new StatisticsData(((WordModel)wordModel).getEnglishWord(((MainAppActivity)activity).mAdapter.getItem(position)).get_id(),1, mDay + ".0"+(mMonth+1) +"."+ (mYear-2000)));
            activity.finish();
        }
        else {
            StatisticsModel.addItem(new StatisticsData(((WordModel)wordModel).getEnglishWord(((MainAppActivity)activity).mAdapter.getItem(position)).get_id(),-1, mDay + ".0"+(mMonth+1) +"."+ (mYear-2000)));

            ((MainAppActivity)activity).setmAdapter();
        }

    }



    private void checkWords(){
        int count;
    for (int i=1;i<StatisticsCountByWord;i++) {
        count = ((StatisticsModel)StatisticsModel).getCountAnswerWord(i);
        if (count >= 3 && wordModel.getItem(i).getWord_knowledge()!=1) {
          wordModel.updateItem(new WordData(wordModel.getItem(i).get_id(),wordModel.getItem(i).getEnglish(),wordModel.getItem(i).getRussian(),wordModel.getItem(i).getTranscription(),wordModel.getItem(i).getPart_speech(),wordModel.getItem(i).getComplexity(),1));

        }
    }
}

    String getEngText(){
    return  wordModel.getItem(integers.get(getOnePosition(integers))).getEnglish();
    }


//???????? 4 ????????? ? ?????????? id ????,??????? ? ?????????? ????? ????????????
ArrayList<Integer> initPosition(){

        int count =0;
        integers = new ArrayList<>();
        complexities = getComplexityWord(complex);
        random=new Random();
        while (count!=4) {

            int r = random.nextInt(((WordModel)wordModel).getWordsCount(complex));
            integers.add(complexities.get(r));
            count++;
            if (count >= 2) {
                for (int i = 0; i < integers.size(); i++) {
                    for (int j = i + 1; j < integers.size(); j++) {
                        if (Objects.equals(integers.get(i), integers.get(j))) {
                            integers.remove(i);
                            count--;
                        }

                    }
                }
            }
        }
        return integers;
    }

    //????? ??? ?????? ?????? ?? 4 ???? ??? ??????????? ????????
    private int getOnePosition(ArrayList<Integer> integers){
        random = new Random();
        return random.nextInt(integers.size());
    }

    // ?????????? ???? ????? ?? ?????? ? ???????????? ? ?????????
    public ArrayList<String> initStringItems(){
        ArrayList<String> items = new ArrayList<>();
        for(int i=0;i<4;i++) {
            String k = wordModel.getItem(integers.get(i)).getRussian();
            items.add(k);
        }

        return items;
    }

    private ArrayList<Integer> getComplexityWord(String complexity){
        complexities = new ArrayList<>();

        for(int i = 0; i<((WordModel)wordModel).getWordsCount(complexity); i++){
            int k = (int) ((WordModel)wordModel).getComplexity(complexity).get(i).get_id();
            complexities.add(k);


        }

        return complexities;
    }


}
