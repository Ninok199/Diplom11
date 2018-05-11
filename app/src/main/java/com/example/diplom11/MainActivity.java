package com.example.diplom11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.diplom11.View.MainAppActivity;
import com.example.diplom11.View.WordTranslateActivity;


public class MainActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_LEVEL_WORDS = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Intent i = new Intent(this, WordTranslateActivity.class);
        startActivity(i);
        finish();
    }

}
