package com.example.harurestaurant.demo.repository;

import com.example.harurestaurant.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}

