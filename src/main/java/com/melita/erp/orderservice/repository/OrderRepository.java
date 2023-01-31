package com.melita.erp.orderservice.repository;

import com.melita.erp.orderservice.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
