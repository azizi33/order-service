package com.melita.erp.orderservice.models.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
@Data
public class Item {
    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private double price;
    @NotBlank
    private int qty;
    private @Valid  List<ItemPackage> packages;

}
