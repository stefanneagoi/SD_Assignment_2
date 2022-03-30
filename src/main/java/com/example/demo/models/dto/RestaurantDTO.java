package com.example.demo.models.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class RestaurantDTO {
    @NotNull
    @Size(min = 6, max = 20, message = "Name must be between 6 and 20 characters!")
    private String name;

    @NotNull
    @Size(min = 6, max = 20, message = "Name must be between 6 and 20 characters!")
    private String location;

    @NotNull
    @Size(min = 6, max = 50, message = "Name must be between 6 and 50 characters!")
    private String deliveryZones;
}
