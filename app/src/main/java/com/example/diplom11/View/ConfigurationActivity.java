package com.example.diplom11.View;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.diplom11.Presenters.ConfigPresenter;
import com.example.diplom11.Presenters.ConfigPresenterImpl;
import com.example.diplom11.R;



public class ConfigurationActivity extends AppCompatActivity  {
    ListView menu;
    ConfigPresenter presenter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        menu = findViewById(R.id.menuItemsConfig);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.itemsOfMenu, android.R.layout.simple_list_item_1);
        menu.setAdapter(adapter);
        presenter = new ConfigPresenterImpl(this);
        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.onItemCLick(i);

            }
        });
    }

            public void onExitClick(View view) {
                presenter.onBackClick();
            }

            public void openActivity(Class<?> cls) {
                intent= new Intent(ConfigurationActivity.this, cls);
                startActivity(intent);

            }

    }

