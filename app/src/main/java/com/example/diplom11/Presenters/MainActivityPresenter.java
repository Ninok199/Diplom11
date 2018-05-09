package com.example.diplom11.Presenters;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.diplom11.Data.DaoSession;
import com.example.diplom11.MainActivity;
import com.example.diplom11.Models.WordModel;
import com.example.diplom11.MyApplication;
import com.example.diplom11.View.MainAppActivity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;



public class MainActivityPresenter implements BasePresenter {

    private MainAppActivity activity;
    private WordModel model;
    private ArrayList<Integer> integers;
    private ArrayList<Integer> complexities;
    private Random random;
     private SharedPreferences mSettings;
     String complex;


    public MainActivityPresenter(MainAppActivity activity){
        this.activity=activity;
        model = new WordModel(activity.getApplicationContext());
        mSettings = activity.getSharedPreferences(MainActivity.APP_PREFERENCES,Context.MODE_PRIVATE);
        complex = mSettings.getString(MainActivity.APP_PREFERENCES_LEVEL_WORDS,"0");






    }

//    public MainActivityPresenter(Context c){
//     this.context=c;
//     model = new WordModel(c);
//    }



    @Override
    public void onBackClick() {

    }

    @Override
    public void onItemCLick(int position) {

        if(Objects.equals(activity.getmSelectText().getText().toString(), model.getRussWord(activity.mAdapter.getItem(position)).getRussian())){
            activity.finish();
        }
        else {
            activity.setmAdapter();
        }

    }



    public String getRussText(){
    return  model.getWord(integers.get(getOnePosition(integers))).getRussian();
    }


//выбирает 4 рандомных и уникальных id слов,которые в дальнейшем будут отображаться
    public ArrayList<Integer> initPosition(){

        int count =0;
        integers = new ArrayList<>();
        complexities = getComplexityWord(complex);
        random=new Random();
        while (count!=4) {

            int r = random.nextInt(model.getWordsCount(Integer.parseInt(complex)));
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

    //метод для выбора одного из 4 слов для дальнейшего перевода
    private int getOnePosition(ArrayList<Integer> integers){
        random = new Random();
        return random.nextInt(integers.size());
    }

    // отображает англ слова на экране в соответствии с индексами
    public ArrayList<String> initStringItems(){
        ArrayList<String> items = new ArrayList<>();
        for(int i=0;i<4;i++) {
            String k = model.getWord(integers.get(i)).getEnglish();
            items.add(k);
        }

        return items;
    }

    public ArrayList<Integer> getComplexityWord(String complexity){
        complexities = new ArrayList<>();
        for(int i = 0; i<model.getWordsCount(Integer.parseInt(complexity)); i++){
            int k = (int) model.getComplexity(complexity).get(i).get_id();
            complexities.add(k);


        }

        return complexities;
    }


}
