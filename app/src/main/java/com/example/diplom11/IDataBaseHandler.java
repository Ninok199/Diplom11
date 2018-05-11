package com.example.diplom11;

import com.example.diplom11.Data.WordData;

import java.util.ArrayList;
import java.util.List;



public interface IDataBaseHandler {
     void addWord(WordData word);
     WordData getWord(int id);
    List<WordData> getAllWords();
    int getWordsCount(String flag);
    int updateWord(WordData word);
     void deleteWord(WordData word);
     void deleteAll();
   List <WordData> getComplexity(String complexity);
}
