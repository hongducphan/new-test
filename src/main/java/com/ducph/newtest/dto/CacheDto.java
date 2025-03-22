package com.ducph.newtest.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CacheDto implements Serializable {

    private String key;
    private String value;
}
