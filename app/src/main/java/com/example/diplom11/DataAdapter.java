package com.example.diplom11;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diplom11.Models.WordModel;
import com.example.diplom11.Presenters.MainActivityPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Created by Инна on 04.05.2018.
 */

public class DataAdapter extends ArrayAdapter<String> {

    private  ArrayList<String> items;
    private static final String[] mContacts = { "Рыжик", "Барсик", "Мурзик",
            "Мурка" };

    Context mContext;
    WordModel model;
    Random random;
    private ArrayList<Integer> integers;
    String pp;
    MainActivityPresenter presenter;

    // Конструктор
    public DataAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId, mContacts);
        // TODO Auto-generated constructor stub
        this.mContext = context;
        model = new WordModel(context);
        presenter = new MainActivityPresenter(context);
        initItems();



    }


    public void initItems(){
        items = new ArrayList<>();
        integers=presenter.initPosition();
        System.out.println(integers);
        for(int i =0;i<4;i++) {
            String k = model.getWord(integers.get(i)).getEnglish();
            items.add(k);
        }
        pp = model.getWord(integers.get(getOnePosition(integers))).getRussian();
        System.out.println(pp);
    }



    public int getOnePosition(ArrayList<Integer> integers){
        ArrayList<Integer> i = integers;
        random = new Random();
         int r = random.nextInt(i.size());
        System.out.println(r + "  hfyl");
        return r;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        TextView label = (TextView) convertView;

        if (convertView == null) {
            convertView = new TextView(mContext);
            label = (TextView) convertView;
        }

        label.setText(items.get(position));
        return (convertView);
    }

    // возвращает содержимое выделенного элемента списка
    public String getItem(int position) {
        return items.get(position);
    }
}
