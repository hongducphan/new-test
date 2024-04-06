package com.ducph.newtest.controller;

import com.ducph.newtest.dto.RedisDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
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
public class RedisController {

    private final RedisTemplate<String, String> redisTemplate;

    @GetMapping("/{key}")
    public ResponseEntity<?> getByKey(@PathVariable String key) {
        String value = redisTemplate.opsForValue().get(key);
        return ResponseEntity.ok(value);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RedisDto data) {
        redisTemplate.opsForValue().set(data.getKey(), data.getValue());
        return ResponseEntity.ok("Create redis data with key: " + data.getKey());
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<?> evictByKey(@PathVariable String key) {
        String value = redisTemplate.opsForValue().getAndDelete(key);
        return ResponseEntity.ok("Deleted redis data with key: " + value);
    }
}
