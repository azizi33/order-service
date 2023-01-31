package com.melita.erp.orderservice.service;

import com.melita.erp.orderservice.models.dto.OrderResponseDto;
import com.melita.erp.orderservice.models.dto.OrderRequestDto;

import java.util.List;

public interface OrderService {
   OrderResponseDto createOrder(OrderRequestDto order);

    List<OrderResponseDto> getOrders();
}
