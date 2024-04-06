package com.ducph.newtest.listener;

import com.ducph.newtest.entity.OrderDetail;
import com.ducph.newtest.entity.OrderDetailHistory;
import com.ducph.newtest.repository.OrderDetailHistoryRepository;
import jakarta.persistence.PostUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.sql.Timestamp;

@Slf4j
public class OrderDetailEventListener implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    @PostUpdate
    public void generateHistory(OrderDetail orderDetail) {
        OrderDetailHistoryRepository orderDetailHistoryRepository = applicationContext.getBean(OrderDetailHistoryRepository.class);
        var now = new Timestamp(System.currentTimeMillis());
        var orderDetailHistory = new OrderDetailHistory();
        orderDetailHistory.setName(orderDetail.getName());
        orderDetailHistory.setCreatedDate(now);
        orderDetailHistory.setUpdatedDate(now);
        orderDetailHistory.setOrderDetails(orderDetail);
        orderDetailHistoryRepository.save(orderDetailHistory);
        log.info("DONE generateHistory");
    }
}
