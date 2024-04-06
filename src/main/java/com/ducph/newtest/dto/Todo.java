package com.ducph.newtest.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Todo implements Serializable {

    private int id;
    private String todo;
    private boolean completed;
    private int userId;
}
