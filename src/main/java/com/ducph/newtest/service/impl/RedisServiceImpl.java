package com.ducph.newtest.service.impl;

import com.ducph.newtest.dto.CacheDto;
import com.ducph.newtest.dto.NotFoundException;
import com.ducph.newtest.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisServiceImpl implements CacheService {

    private final CacheManager cacheManager;
    private static final String CACHE_NAME = "redisData";

    @Override
    @Cacheable(key = "#cache.key", value = CACHE_NAME)
    public String create(CacheDto cache) {
        log.info("Create redis data with key: {}", cache.getKey());
        return cache.getValue();
    }

    @Override
    public String get(String key) {
        Cache redisCache = cacheManager.getCache(CACHE_NAME);
        if (redisCache == null) {
            log.warn("Redis cache not found with name: {}", CACHE_NAME);
            throw new NotFoundException("Redis cache not found with name: " + CACHE_NAME);
        }

        Cache.ValueWrapper valueWrapper = redisCache.get(key);
        if (valueWrapper == null) {
            log.debug("No value found for key: {}", key);
            throw new NotFoundException("No value found for key: " + key);
        }

        return String.valueOf(valueWrapper.get());
    }

    @Override
    @CacheEvict(key = "#key", value = CACHE_NAME)
    public void evict(String key) {
    }
}
