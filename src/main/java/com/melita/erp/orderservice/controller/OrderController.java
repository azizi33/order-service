package com.melita.erp.orderservice.controller;

import com.melita.erp.orderservice.models.dto.OrderRequestDto;
import com.melita.erp.orderservice.models.dto.OrderResponseDto;
import com.melita.erp.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/createOrder", consumes = "application/json", produces = "application/json")
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto){
         return  ResponseEntity.ok(orderService.createOrder(orderRequestDto));
    }

    @PostMapping
    public ResponseEntity<?> getOrders(){
        return  ResponseEntity.ok(orderService.getOrders());
    }

}
