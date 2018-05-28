package com.example.diplom11.Application.Database;

import com.example.diplom11.Application.Database.Entity.StatisticsData;
import com.example.diplom11.Application.Database.Entity.WordData;

import java.io.IOException;
import java.util.List;



public interface IDataBaseHandler {
    void createDataBase() throws IOException;
    boolean checkDataBase();
    void copyDataBase() throws IOException;
    void openDataBase();
    void close();

   }

