package com.ducph.newtest.service;

import com.ducph.newtest.dto.OrderDetailDTO;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetailDTO> fetchAll();

    OrderDetailDTO fetchById(int id);

    OrderDetailDTO updateOrderById(OrderDetailDTO orderDetailDTO);
}
