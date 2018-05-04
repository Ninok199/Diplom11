package com.example.diplom11.Presenters;



import com.example.diplom11.View.ChoiceLevelActivity;
import com.example.diplom11.View.ConfigurationActivity;



public class ConfigPresenterImpl implements ConfigPresenter {

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

        }
    }
}
