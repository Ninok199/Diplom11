package com.example.diplom11;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.diplom11.Application.Database.DatabaseWordService;
import com.example.diplom11.Application.Database.Entity.WordData;
import com.example.diplom11.Application.Model.WordModel;
import com.example.diplom11.Application.Presenters.WordTranslatePresenter;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Инна on 04.06.2018.
 */

public class WordTranslatePresenterTest {

    private List<WordData> data = new ArrayList<>(Arrays.asList(new WordData(32, "go", "идти", "ssss", 3, 3, 0),
            new WordData(33, "apple", "яблоко", "pppp", 1, 2, 0),
            new WordData(34, "melon", "дыня", "kkkk", 1, 1, 0)));



    @Test
    public void initEnglishItem() {
        DatabaseWordService db = mock(DatabaseWordService.class);
        when(db.getWord(1)).thenReturn(data.get(1));
        WordModel model = new WordModel(db);
        WordTranslatePresenter p = new WordTranslatePresenter(model, 1);
        Assert.assertEquals(p.initEnglishItem(), data.get(1).getEnglish());
    }
    @Test
    public void initPartSpeech(){
        DatabaseWordService db = mock(DatabaseWordService.class);
        when(db.getWord(1)).thenReturn(data.get(0));
        WordModel model = new WordModel(db);
        WordTranslatePresenter p = new WordTranslatePresenter(model, 1);
        Assert.assertEquals(p.initPartSpeechItem(),"verb");
        }

    @Test
    public void initRussianItem(){
            DatabaseWordService db = mock(DatabaseWordService.class);
            when(db.getWord(2)).thenReturn(data.get(2));
            WordModel model = new WordModel(db);
            WordTranslatePresenter p = new WordTranslatePresenter(model, 2);
            Assert.assertEquals(p.initRussItem(),"дыня");
        }


    @Test
    public void initTranscription(){
        DatabaseWordService db = mock(DatabaseWordService.class);
        when(db.getWord(1)).thenReturn(data.get(1));
        WordModel model = new WordModel(db);
        WordTranslatePresenter p = new WordTranslatePresenter(model, 1);
        Assert.assertEquals(p.initTranscriptionItem(),"pppp");
    }
}


