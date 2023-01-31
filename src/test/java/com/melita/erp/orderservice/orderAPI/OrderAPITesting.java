package com.melita.erp.orderservice.orderAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.melita.erp.orderservice.config.SecurityConfig;
import com.melita.erp.orderservice.controller.OrderController;
import com.melita.erp.orderservice.models.dto.OrderRequestDto;
import com.melita.erp.orderservice.models.dto.OrderResponseDto;
import com.melita.erp.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {OrderController.class}, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@AutoConfigureMockMvc
@ContextConfiguration(classes={SecurityConfig.class})
public class OrderAPITesting {
    @Mock
    OrderService orderService;
    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    OrderController orderController;

    List<OrderResponseDto> orderList=new ArrayList<>();
    @BeforeEach
    public void loadOrders(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        OrderResponseDto responseDto=new OrderResponseDto();
        responseDto.setOrderDate(new Date());
        responseDto.setOrderNumber(100001);
        responseDto.setCustomerName("John");
        orderList.add(responseDto);

    }

    @Test
    public void createOrder() throws Exception {
        OrderRequestDto request=new OrderRequestDto();
        request.setEmail("test_john@gmail.com");
        request.setCustomerName("John");
        request.setCustomerPhone("10210210202");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/order")
                        .content(new ObjectMapper().writeValueAsString(request)).
                        header("auth-user","string")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
