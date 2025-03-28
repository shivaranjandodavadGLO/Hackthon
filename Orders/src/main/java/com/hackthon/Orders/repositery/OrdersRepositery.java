package com.hackthon.Orders.repositery;

import com.hackthon.Orders.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepositery extends JpaRepository<Orders, String> {
    List<Orders> findByUserId(String userId);
}
