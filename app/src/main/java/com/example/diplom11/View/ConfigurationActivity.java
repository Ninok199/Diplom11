package com.example.diplom11.View;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diplom11.MainActivity;
import com.example.diplom11.Presenters.BasePresenter;
import com.example.diplom11.Presenters.ConfigPresenterImpl;
import com.example.diplom11.R;



public class ConfigurationActivity extends AppCompatActivity  {
    ListView menu;
    BasePresenter presenter;
    Intent intent;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        menu = findViewById(R.id.menuItemsConfig);
        textView =findViewById(R.id.textView);
        textView.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/11364.ttf"));
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

