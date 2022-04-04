package com.example.demo.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private Integer orderId;
    private Integer customerId;
    private List<FoodDTO> foods;
    private String status;
}
