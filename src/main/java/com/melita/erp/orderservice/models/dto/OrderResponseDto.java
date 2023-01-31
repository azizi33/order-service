package com.melita.erp.orderservice.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderResponseDto {
    private long orderNumber;
    private String CustomerName;
    private Date OrderDate;
}
