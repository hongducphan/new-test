package com.ducph.newtest.service;

import com.ducph.newtest.dto.Todo;
import com.ducph.newtest.dto.TodoList;

public interface TodoService {

    TodoList fetchAll();

    Todo findById(int id);

    void evict();
}
