package com.example.diplom11;

import android.app.Activity;
import android.content.Context;
import android.test.suitebuilder.annotation.SmallTest;

import com.example.diplom11.Application.Database.DataBaseHandlerImpl;
import com.example.diplom11.Application.Database.DatabaseWordService;
import com.example.diplom11.Application.Database.Entity.WordData;
import com.example.diplom11.Application.Model.WordModel;
import com.example.diplom11.Application.Presenters.ChoiceLevelPresenterImpl;
import com.example.diplom11.Application.Presenters.ChoiceModePresenterImpl;
import com.example.diplom11.Application.Presenters.ConfigPresenterImpl;
import com.example.diplom11.Application.View.MainActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Инна on 03.06.2018.
 */
@SmallTest
public class WordServiceTest {
    private List<WordData> data = new ArrayList<>(Arrays.asList(new WordData(32,"go", "идти","ssss",3,3,0),
            new WordData(33,"apple", "яблоко","ssss",1,2,0),
            new WordData(34,"melon", "дыня","ssss",1,1,0)));


    @Test
    public void testMockGetWord() {
        DatabaseWordService dbHelper = Mockito.mock(DatabaseWordService.class);
        when(dbHelper.getWord(33)).thenReturn(data.get(1));
        Assert.assertEquals(dbHelper.getWord(33), data.get(1));
    }

    @Test
    public void getCount(){
        DatabaseWordService dbHelper = Mockito.mock(DatabaseWordService.class);
        when(dbHelper.getAllWordsCount()).thenReturn(data.size());
        Assert.assertEquals(dbHelper.getAllWordsCount(), 3);
    }


    @Test
    public void getAllWords(){
        DatabaseWordService dbHelper = Mockito.mock(DatabaseWordService.class);
        when(dbHelper.getAllWords()).thenReturn(data);
        Assert.assertEquals(dbHelper.getAllWords(),data);
    }



}


