package com.ducph.newtest.repository;

import com.ducph.newtest.entity.OrderDetailHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailHistoryRepository extends JpaRepository<OrderDetailHistory, Integer> {
}
