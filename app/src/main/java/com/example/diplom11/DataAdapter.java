package com.example.diplom11;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diplom11.Presenters.MainActivityPresenter;
import com.example.diplom11.View.MainAppActivity;

import java.util.ArrayList;

/**
 * Created by Инна on 04.05.2018.
 */

public class DataAdapter extends ArrayAdapter<String> {

    private  ArrayList<String> items;
    private static final String[] mContacts = { "Рыжик", "Барсик", "Мурзик",
            "Мурка" };

    Context mContext;
    private ArrayList<Integer> integers;
    MainActivityPresenter presenter;

    // Конструктор
    public DataAdapter(Context context, int textViewResourceId, MainAppActivity activity) {
        super(context, textViewResourceId, mContacts);
        // TODO Auto-generated constructor stub
        this.mContext = context;
        presenter = new MainActivityPresenter(activity);
        initItems();


    }


    public void initItems(){
        items = new ArrayList<>();
        integers=presenter.initPosition();
        for (int i=0;i<4;i++){
            String k = presenter.initStringItems().get(i);
            items.add(k);
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



    public String getTextFromPresenter(){
        return presenter.getRussText();
    }


}
