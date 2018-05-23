package com.example.diplom11.Application.Database;

import com.example.diplom11.Application.Database.Entity.StatisticsData;
import com.example.diplom11.Application.Database.Entity.WordData;

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
   StatisticsData getStatistics (int id);
   List<StatisticsData> getAllStatistics();
    int getStatisticsCount();
    int getCountAnswerWord(int id);
    public String getDataAnswerWord(int id);
    public int getStatisticsCountByWord() ;
    void addStatistics(StatisticsData StatisticsData);
    public int updateStatistics(StatisticsData StatisticsData);

   }

