package com.example.demo.models.dto;
import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class FoodDTO {
    private Integer id;

    @NotNull
    @Size(min = 3, max = 20, message = "Must be between 3 and 20 characters!")
    private String name;

    @NotNull
    private String category;

    @NotNull
    private Integer price;

    @NotNull
    @Size(min = 10, max = 50, message = "Must be between 10 and 50 characters!")
    private String description;
}
