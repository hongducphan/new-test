package com.ducph.newtest.controller;

import com.ducph.newtest.dto.OrderDetailDTO;
import com.ducph.newtest.service.OrderDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-detail")
@RequiredArgsConstructor
@Slf4j
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<?> fetchAll() {
        return ResponseEntity.ok(orderDetailService.fetchAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> fetchById(@PathVariable int id) {
        return ResponseEntity.ok(orderDetailService.fetchById(id));
    }

    @PutMapping
    public ResponseEntity<?> updateById(@RequestBody @Valid OrderDetailDTO orderDetailDTO) {
        return ResponseEntity.ok(orderDetailService.updateOrderById(orderDetailDTO));
    }
}
