package com.melita.erp.orderservice.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerAddress {
    @NotBlank
    private String Country;
    @NotBlank
    private String City;
    @NotBlank
    private String street;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String address;
}
