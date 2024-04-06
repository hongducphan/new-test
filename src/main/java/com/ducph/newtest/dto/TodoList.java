package com.ducph.newtest.dto;

import lombok.Data;

import java.util.List;

@Data
public class TodoList {

    private List<Todo> todos;
    private int total;
    private int skip;
    private int limit;
}
