package com.melita.erp.orderservice.models.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import java.util.UUID;

@Data
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(unique = true)
    private String username;
    private String password;
}
