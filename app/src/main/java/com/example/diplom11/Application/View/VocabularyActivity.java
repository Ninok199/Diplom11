package com.example.diplom11.Application.View;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.diplom11.Application.Presenters.VocabularyPresenter;
import com.example.diplom11.R;

/**
 * класс, отвечающий за отображение словаря пользователю
 */
public class VocabularyActivity extends AppCompatActivity {

    TableLayout tableLayout;
    VocabularyPresenter presenter;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        presenter = new VocabularyPresenter(this);
        tableLayout = findViewById(R.id.table);
        textView=findViewById(R.id.textView5);
        textView.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/11364.ttf"));

        initData();

    }

    /**
     * добавление данный в строки таблицы для отображения
     */

    public void initData(){

        for (int i=1;i<presenter.getWordCount()+1;i++) {
            addRow(presenter.initEnglishItem(i),presenter.initTranscriptionItem(i),presenter.initRussItem(i),presenter.initPartSpeechItem(i));

        }
        }

    /**
     * создание таблицы
     * @param c0 англ значение
     * @param c1 транскрипция
     * @param c2 русс значение
     * @param c3 часть речи
     */

    public void addRow(String c0, String c1, String c2, String c3) {
        //Сначала найдем в разметке активити саму таблицу по идентификатору
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        //Создаём экземпляр инфлейтера, который понадобится для создания строки таблицы из шаблона. В качестве контекста у нас используется сама активити
        LayoutInflater inflater = LayoutInflater.from(this);
        //Создаем строку таблицы, используя шаблон из файла /res/layout/table_row.xml
        TableRow tr = (TableRow) inflater.inflate(R.layout.table_row, null);
        //Находим ячейку для номера дня по идентификатору
        TextView tv = (TextView) tr.findViewById(R.id.col1);
        //Обязательно приводим число к строке, иначе оно будет воспринято как идентификатор ресурса
        tv.setText(c0);
        //Ищем следующую ячейку и устанавливаем её значение
        tv = (TextView) tr.findViewById(R.id.col2);
        tv.setText(c1);
        tv = (TextView) tr.findViewById(R.id.col3);
        tv.setText(c2);
        tv = (TextView) tr.findViewById(R.id.col4);
        tv.setText(c3);
        //...и так далее для всех значений
        tableLayout.addView(tr); //добавляем созданную строку в таблицу
    }


    public void onBackClick(View view) {
        presenter.onBackClick();
    }

    public void onSaveClick(View view) {
        presenter.addNewWord();
    }
}
