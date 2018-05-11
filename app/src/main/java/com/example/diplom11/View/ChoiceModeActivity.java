package com.example.diplom11.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.diplom11.Presenters.BasePresenter;
import com.example.diplom11.Presenters.ChoiceModePresenterImpl;
import com.example.diplom11.R;

/**
 * Created by Инна on 04.05.2018.
 */

public class ChoiceModeActivity extends AppCompatActivity{
    ListView menu;
    BasePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_mode);
        menu = findViewById(R.id.listModeView);
        menu.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.itemsOfChoiceMode,android.R.layout.simple_list_item_single_choice);
        menu.setAdapter(adapter);
        presenter = new ChoiceModePresenterImpl(this);
        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.onItemCLick(i);
            }
        });


    }

    public void onBackClick(View view){
        presenter.onBackClick();
    }
}

