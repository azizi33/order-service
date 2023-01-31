package com.melita.erp.orderservice.models.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Data
@Slf4j
public class OrderRequestDto {
    @NotBlank
    private String customerName;
    @NotBlank
    private String customerPhone;
    @NotBlank
    @Email
    private String email;
    private CustomerAddress customeraddress;
    private @Valid List<Item> products;
}
