package com.example.diplom11;

import java.util.List;

/**
 * Created by Инна on 04.05.2018.
 */

public interface IDataBaseHandler {
    public void addWord(WordData word);
    public WordData getWord(int id);
    public List<WordData> getAllWords();
    public int getWordsCount();
    public int updateWord(WordData word);
    public void deleteWord(WordData word);
    public void deleteAll();
}
