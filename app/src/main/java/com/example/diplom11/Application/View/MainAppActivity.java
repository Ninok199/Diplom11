package com.example.diplom11.Application.View;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.diplom11.Application.Presenters.DataAdapter;
import com.example.diplom11.Application.Presenters.MainActivityPresenter;
import com.example.diplom11.R;

/**
 * класс, отвечающий за отображение элементов в режиме вопрос-ответ
 */

public class MainAppActivity extends AppCompatActivity {
    Intent intent;
    private TextView mSelectText;
    public DataAdapter mAdapter;
    MainActivityPresenter presenter;
    GridView g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);

        mSelectText = findViewById(R.id.textView4);
        mSelectText.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/11364.ttf"));
        g =  findViewById(R.id.gridbuttonsview);
        setmAdapter();


        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                presenter.onItemCLick(position);
            }
        });


    }

    /**
     * уставка адаптера для отображения текста на экране
     */

public void setmAdapter(){
    mAdapter = new DataAdapter(this,
            android.R.layout.simple_list_item_1,this);
              g.setAdapter(mAdapter);
    getmSelectText().setText( mAdapter.getTextFromPresenter());
}

    public void onClickConfigMenu(View view) {

        intent = new Intent(MainAppActivity.this, com.example.diplom11.Application.View.ConfigurationActivity.class);
        startActivity(intent);
        finish();


    }

    public TextView getmSelectText() {
        return mSelectText;
    }


}
