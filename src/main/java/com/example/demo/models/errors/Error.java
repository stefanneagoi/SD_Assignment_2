package com.example.demo.models.errors;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class Error {
    private HttpStatus httpStatus;

    private LocalDateTime timestamp;

    private String message;

    private String details;

    public Error(HttpStatus httpStatus, String message, String details) {
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.details = details;
    }
}
