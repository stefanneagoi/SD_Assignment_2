package com.example.demo.models.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class RegisterDTO {
    @NotNull
    @Size(min = 6, max = 16, message = "Must be between 6 and 16 characters!")
    @Email(message = "Must have a valid email format!")
    private String username;

    @NotNull
    private String passwordDigest;
}
