package com.example.diplom11;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Инна on 04.05.2018.
 */

public class DataAdapter extends ArrayAdapter<String> {


    private static final String[] mContacts = { "Рыжик", "Барсик", "Мурзик",
            "Мурка" };

    Context mContext;

    // Конструктор
    public DataAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId, mContacts);
        // TODO Auto-generated constructor stub
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        TextView label = (TextView) convertView;

        if (convertView == null) {
            convertView = new TextView(mContext);
            label = (TextView) convertView;
        }
        label.setText(mContacts[position]);
        return (convertView);
    }

    // возвращает содержимое выделенного элемента списка
    public String getItem(int position) {
        return mContacts[position];
    }
}
