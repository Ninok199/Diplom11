package com.example.diplom11.Application.Presenters;



import com.example.diplom11.Application.View.AboutAppActivity;
import com.example.diplom11.Application.View.ChoiceLevelActivity;
import com.example.diplom11.Application.View.ChoiceModeActivity;
import com.example.diplom11.Application.View.ConfigurationActivity;
import com.example.diplom11.Application.View.StatisticsActivity;
import com.example.diplom11.Application.View.VocabularyActivity;


public class ConfigPresenterImpl implements BasePresenter {

    private ConfigurationActivity activity;


    public ConfigPresenterImpl(ConfigurationActivity activity){
        this.activity = activity;

    }

    @Override
    public void onBackClick() {
         activity.finish();
    }

    @Override
    public void onItemCLick( int position) {
        switch (position){
            case 0:
                activity.openActivity(ChoiceLevelActivity.class);
                break;
            case 1:
                activity.openActivity(ChoiceModeActivity.class);
                break;
            case 2:
                activity.openActivity(StatisticsActivity.class);
                break;

            case 3:
                activity.openActivity(VocabularyActivity.class);
                break;
            case 4:
                activity.openActivity(AboutAppActivity.class);
                break;
        }
    }
}
