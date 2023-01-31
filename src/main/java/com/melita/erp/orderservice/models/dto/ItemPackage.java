package com.melita.erp.orderservice.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
@Data
public class ItemPackage {
    @NotBlank
    private String name;
    private String description;
    private Date validTo;
}
