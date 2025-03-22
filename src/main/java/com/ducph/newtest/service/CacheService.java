package com.ducph.newtest.service;

import com.ducph.newtest.dto.CacheDto;

public interface CacheService {

    String create(CacheDto data);

    String get(String key);

    void evict(String key);
}
