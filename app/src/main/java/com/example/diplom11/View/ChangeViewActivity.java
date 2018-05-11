package com.example.diplom11.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.diplom11.Presenters.BasePresenter;
import com.example.diplom11.Presenters.ChangeViewPresenterImpl;
import com.example.diplom11.R;

/**
 * Created by Инна on 04.05.2018.
 */

public class ChangeViewActivity extends AppCompatActivity {

    ListView menu;

    BasePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_view);
        menu = findViewById(R.id.ListViewMenu);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.itemsOfViewMenu, android.R.layout.simple_list_item_1);
        menu.setAdapter(adapter);
        presenter = new ChangeViewPresenterImpl(this);

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
                presenter.onItemCLick(i);
            }
        });

    }
    public void onBackClick(View view) {
        presenter.onBackClick();
    }
}


