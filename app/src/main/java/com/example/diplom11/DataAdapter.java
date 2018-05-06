package com.example.diplom11;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diplom11.Models.WordModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Инна on 04.05.2018.
 */

public class DataAdapter extends ArrayAdapter<String> {

    private static ArrayList<String> items;
    private static final String[] mContacts = { "Рыжик", "Барсик", "Мурзик",
            "Мурка" };

    Context mContext;
    WordModel model;

    // Конструктор
    public DataAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId, mContacts);
        // TODO Auto-generated constructor stub
        this.mContext = context;
        model = new WordModel(context);
        initItems();


    }

    public void initItems(){
        items = new ArrayList<>();

        for (int i=1;i<5;i++){
            String k = model.getWord(i).getEnglish();
            items.add(k);
            System.out.println(k);
        }

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
