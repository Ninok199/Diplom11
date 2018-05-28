package com.example.diplom11.Application.Model;

import java.util.List;

/**
 * Created by Инна on 23.05.2018.
 */

public interface BaseModel<T> {
     void addItem(T item);
     int getAllCount();
     List<T> getAllItems();
     T getItem(int id);
     void updateItem(T item);

}
