package com.example.diplom11.View;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.diplom11.R;

public class AboutAppActivity extends AppCompatActivity {
TextView title;
TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        title = findViewById(R.id.textView6);
        text = findViewById(R.id.textView8);
        title.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/11364.ttf"));
        text.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/11364.ttf"));
    }

    public void onBackClick(View view) {
        finish();
    }
}
