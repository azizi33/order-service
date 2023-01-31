package com.melita.erp.orderservice.service;

import com.melita.erp.orderservice.models.dto.OrderResponseDto;
import com.melita.erp.orderservice.models.dto.OrderRequestDto;
import com.melita.erp.orderservice.models.entities.Order;
import com.melita.erp.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    KafkaTemplate kafkaTemplate;


    @Override
    public OrderResponseDto createOrder(OrderRequestDto order) {
        long orderN=generateOrderNumber();
        OrderResponseDto responseDto=new OrderResponseDto();
        responseDto.setOrderNumber(orderN);
        responseDto.setOrderDate(new Date());
        responseDto.setCustomerName(order.getCustomerName());
     //   kafkaTemplate.send("Orders",responseDto);
        return responseDto;
    }

    @Override
    public List<OrderResponseDto> getOrders() {
       return  orderRepository.findAll().stream().map(order->convertorDto(order)).collect(Collectors.toList());
    }

    private long generateOrderNumber(){
        Random rand = new Random();
       int orderNum = rand.nextInt((999999 - 100) + 1) + 100;
        return orderNum;
    }

   private OrderResponseDto  convertorDto(Order order){
      OrderResponseDto responseDto=new OrderResponseDto();
      responseDto.setOrderNumber(order.getId());
      responseDto.setCustomerName(order.getCustomerName());
      responseDto.setOrderDate(order.getOrderDate());
      return responseDto;
  }

}
