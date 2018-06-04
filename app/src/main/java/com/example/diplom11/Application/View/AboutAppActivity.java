package com.example.diplom11.Application.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diplom11.Application.Database.Entity.WordData;
import com.example.diplom11.R;

/**
 * окно с отображением информации о приложении
 */
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

    public void onHelpClick(View view) {


        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Инструкция пользования");
        alert.setMessage(R.string.info);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }

        });


        alert.show();
    }

}
