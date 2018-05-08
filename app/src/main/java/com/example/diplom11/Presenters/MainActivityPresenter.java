package com.example.diplom11.Presenters;

import android.content.Context;
import com.example.diplom11.Models.WordModel;
import com.example.diplom11.View.MainAppActivity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;



public class MainActivityPresenter implements BasePresenter {
    private Context context;
    private MainAppActivity activity;
    private WordModel model;
    private ArrayList<Integer> integers;
    private Random random;



    public MainActivityPresenter(MainAppActivity activity,Context c){
        this.activity=activity;
        this.context =c;
        model = new WordModel(c);

    }

    public MainActivityPresenter(Context c){
     this.context=c;
     model = new WordModel(c);
    }



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
        random=new Random();
        while (count!=4) {
            int r = random.nextInt(10) + 1;
            integers.add(r);
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
        for(int i =0;i<4;i++) {
            String k = model.getWord(integers.get(i)).getEnglish();
            items.add(k);
        }

        return items;
    }



}
