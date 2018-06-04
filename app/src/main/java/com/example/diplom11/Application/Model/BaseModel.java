package com.example.diplom11.Application.Model;

import java.util.List;

/**
 * интерфейс для моделей данных
 */

public interface BaseModel<T> {
     void addItem(T item);
     int getAllCount();
     List<T> getAllItems();
     T getItem(int id);
     void updateItem(T item);

}
