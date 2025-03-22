package com.ducph.newtest.controller;

import com.ducph.newtest.dto.CacheDto;
import com.ducph.newtest.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
@Slf4j
public class CacheController {

    private final CacheService cacheService;

    @GetMapping("/{key}")
    public ResponseEntity<?> getByKey(@PathVariable String key) {
        String value = cacheService.get(key);
        return ResponseEntity.ok(value);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CacheDto data) {
        var cacheData = cacheService.create(data);
        return ResponseEntity.ok(cacheData);
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<?> evictByKey(@PathVariable String key) {
        cacheService.evict(key);
        return ResponseEntity.ok("Deleted redis data with key: " + key);
    }
}
