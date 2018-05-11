package com.example.diplom11.Presenters;



import com.example.diplom11.View.ChangeViewActivity;
import com.example.diplom11.View.ChoiceLevelActivity;
import com.example.diplom11.View.ChoiceModeActivity;
import com.example.diplom11.View.ConfigurationActivity;
import com.example.diplom11.View.VocabularyActivity;


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
            case 4:
              activity.openActivity(ChangeViewActivity.class);
                break;
            case 3:
                activity.openActivity(VocabularyActivity.class);
                break;
        }
    }
}
