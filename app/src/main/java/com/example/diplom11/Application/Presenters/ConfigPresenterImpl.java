package com.example.diplom11.Application.Presenters;



import android.support.v7.app.AppCompatActivity;

import com.example.diplom11.Application.View.AboutAppActivity;
import com.example.diplom11.Application.View.ChoiceLevelActivity;
import com.example.diplom11.Application.View.ChoiceModeActivity;
import com.example.diplom11.Application.View.ConfigurationActivity;
import com.example.diplom11.Application.View.StatisticsActivity;
import com.example.diplom11.Application.View.VocabularyActivity;

/**
 * класс, отвечающий за логику окна настроек. Благодаря нему производится переход
 * ко всем остальным окнам
 */
public class ConfigPresenterImpl implements BasePresenter {

    private AppCompatActivity activity;


    public ConfigPresenterImpl(AppCompatActivity activity){
        this.activity = activity;

    }

    @Override
    public void onBackClick() {
         activity.finish();
    }

    /**
     * слушатель для пунктов меню настроек программы
     * в зависимости от выбора пользователя, открываеться соответствующее окно
     * @param position номер пункта меню
     */
    @Override
    public void onItemCLick( int position) {
        switch (position){
            case 0:
                ((ConfigurationActivity)activity).openActivity(ChoiceLevelActivity.class);
                break;
            case 1:
                ((ConfigurationActivity)activity).openActivity(ChoiceModeActivity.class);
                break;
            case 2:
                ((ConfigurationActivity)activity).openActivity(StatisticsActivity.class);
                break;

            case 3:
                ((ConfigurationActivity)activity).openActivity(VocabularyActivity.class);
                break;
            case 4:
                ((ConfigurationActivity)activity).openActivity(AboutAppActivity.class);
                break;
        }
    }


}
