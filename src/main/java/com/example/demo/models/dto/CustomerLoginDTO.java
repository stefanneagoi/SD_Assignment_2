package com.example.demo.models.dto;

import lombok.Data;

@Data
public class CustomerLoginDTO {
    private Integer id;
    private Integer customerId;
    private String username;
}
