package com.ducph.newtest.controller;

import com.ducph.newtest.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
@Slf4j
public class Controller {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(todoService.fetchAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        log.info("[findById] - id={}", id);
        return ResponseEntity.ok(todoService.findById(id));
    }

    @GetMapping("/evict")
    public ResponseEntity<?> evict() {
        todoService.evict();
        return ResponseEntity.ok().build();
    }
}
