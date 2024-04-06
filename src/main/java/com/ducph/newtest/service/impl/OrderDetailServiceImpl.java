package com.ducph.newtest.service.impl;

import com.ducph.newtest.dto.NotFoundException;
import com.ducph.newtest.dto.OrderDetailDTO;
import com.ducph.newtest.repository.OrderDetailRepository;
import com.ducph.newtest.service.OrderDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ObjectMapper objectMapper;

    @Override
    public List<OrderDetailDTO> fetchAll() {
        var orderDetails = orderDetailRepository.findAll();
        return orderDetails.stream()
                .map(orderDetail -> objectMapper.convertValue(orderDetail, OrderDetailDTO.class))
                .toList();
    }

    @Override
    public OrderDetailDTO fetchById(int id) {
        log.info(this.getClass().getSimpleName() + ".[fetchById] id: {}", id);
        var orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found :" + id));
        return objectMapper.convertValue(orderDetail, OrderDetailDTO.class);
    }

    @Override
    public OrderDetailDTO updateOrderById(OrderDetailDTO orderDetailDTO) {
        int orderDetailId = orderDetailDTO.getId();
        log.info(this.getClass().getSimpleName() + ".[updateOrderById] id to update: {}", orderDetailId);
        var orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new NotFoundException("Not found :" + orderDetailId));
        orderDetail.setName(orderDetailDTO.getName());
        orderDetail.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        var updatedOrderDetail = orderDetailRepository.save(orderDetail);
        log.info("Updated order detail: {}", updatedOrderDetail);
        return objectMapper.convertValue(updatedOrderDetail, OrderDetailDTO.class);
    }
}
