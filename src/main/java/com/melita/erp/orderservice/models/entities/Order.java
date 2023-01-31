package com.melita.erp.orderservice.models.entities;

import com.melita.erp.orderservice.models.dto.CustomerAddress;
import com.melita.erp.orderservice.models.dto.Item;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
@Entity
@Table(name="Order")
@Data
public class Order {
    @Id
    private long id;
    @NotBlank
    private String customerName;
    @NotBlank
    private String customerPhone;
    @NotBlank
    private String email;
    private CustomerAddress customeraddress;
    private List<Item> products;
    private Date orderDate;

}
