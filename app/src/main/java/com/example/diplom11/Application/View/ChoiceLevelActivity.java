package com.example.diplom11.Application.View;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diplom11.Application.Presenters.ChoiceLevelPresenterImpl;
import com.example.diplom11.R;



public class ChoiceLevelActivity extends AppCompatActivity {
    public ListView menu;
    ChoiceLevelPresenterImpl presenter;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choicelevel);
        menu = findViewById(R.id.listLevelView);
        textView =findViewById(R.id.textView2);
        textView.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/11364.ttf"));
        menu.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.itemsOfChoiceLevels,android.R.layout.simple_list_item_multiple_choice);
        menu.setAdapter(adapter);
        presenter = new ChoiceLevelPresenterImpl(this);
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

    public void onSaveClick(View view) {
        presenter.onButtonSave();
    }
}
