package com.example.diplom11.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.diplom11.R;



public class ChoiceLevelActivity extends AppCompatActivity {
    ListView menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choicelevel);
        menu = findViewById(R.id.listLevelView);
        menu.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.itemsOfChoiceLevels,android.R.layout.simple_list_item_multiple_choice);
        menu.setAdapter(adapter);


    }

    public void onBackClick(View view) {
        finish();
    }
}
