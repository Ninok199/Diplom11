package com.example.diplom11.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.diplom11.DataAdapter;
import com.example.diplom11.Presenters.MainActivityPresenter;
import com.example.diplom11.R;
import com.example.diplom11.WordData;
import com.example.diplom11.Models.WordModel;


public class MainAppActivity extends AppCompatActivity {
    Intent intent;
    public TextView mSelectText;
    public DataAdapter mAdapter;
    MainActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);
        mSelectText = (TextView) findViewById(R.id.textView4);
        final GridView g = (GridView) findViewById(R.id.gridbuttonsview);
        mAdapter = new DataAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1);
        g.setAdapter(mAdapter);
        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                presenter.onItemCLick(position);
            }
        });


//        WordModel model = new WordModel(this);
//        model.initDB();
//        model.addWord(new WordData("qqqq","ssss","aaaa",1,2,3));
    }
//
//        System.out.println("Reading all contacts..");
//        List<WordData> contacts = db.getAllWords();
//        for (WordData cn : contacts) {
//            String log = cn.get_id()+cn.getEnglish()+cn.getRussian()+cn.getComplexity();
//            System.out.print("Name: ");
//            System.out.println(log);
//        }
//    }

    public void onClickConfigMenu(View view) {


        intent = new Intent(MainAppActivity.this, com.example.diplom11.View.ConfigurationActivity.class);
        startActivity(intent);
        finish();


    }
}
