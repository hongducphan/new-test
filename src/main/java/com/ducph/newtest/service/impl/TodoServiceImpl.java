package com.ducph.newtest.service.impl;

import com.ducph.newtest.dto.Todo;
import com.ducph.newtest.dto.TodoList;
import com.ducph.newtest.external.TodoClient;
import com.ducph.newtest.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoServiceImpl implements TodoService {

    private final TodoClient todoClient;

    @Override
    public TodoList fetchAll() {
        return todoClient.fetchAll();
    }

    @Override
    @Cacheable(cacheNames = "todo", key = "#id")
    public Todo findById(int id) {
        log.info("Fetching data from todo server with id - [{}]", id);
        return todoClient.findById(id);
    }

    @CacheEvict(cacheNames = "todo", allEntries = true)
    public void evict() {
    }
}
