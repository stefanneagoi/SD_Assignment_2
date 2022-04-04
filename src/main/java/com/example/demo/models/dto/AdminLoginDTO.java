package com.example.demo.models.dto;

import com.example.demo.models.entities.Restaurant;
import lombok.Data;

@Data
public class AdminLoginDTO {
    private Integer id;
    private Integer adminId;
    private String username;
    private RestaurantDTO restaurant;

}
