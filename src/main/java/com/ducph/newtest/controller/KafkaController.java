package com.ducph.newtest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
@Slf4j
public class KafkaController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/{message}")
    public ResponseEntity<?> publish(@PathVariable String message) {
        kafkaTemplate.send("ducph", message);
        return ResponseEntity.ok("Sent message: " + message);
    }

    @KafkaListener(topics = "ducph", groupId = "ducph-group")
    public void listen(String message) {
        log.info("Received message: {}", message);
    }
}
