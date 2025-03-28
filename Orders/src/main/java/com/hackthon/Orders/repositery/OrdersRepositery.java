package com.hackthon.Orders.repositery;

import com.hackthon.Orders.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepositery extends JpaRepository<Orders, String> {
}
