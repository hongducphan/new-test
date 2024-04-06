package com.ducph.newtest.external;

import com.ducph.newtest.dto.Todo;
import com.ducph.newtest.dto.TodoList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface TodoClient {

    @GetExchange
    TodoList fetchAll();

    @GetExchange("/{id}")
    Todo findById(@PathVariable int id);
}
