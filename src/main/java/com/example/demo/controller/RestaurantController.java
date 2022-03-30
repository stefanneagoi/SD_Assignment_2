package com.example.demo.controller;
import com.example.demo.models.dto.RestaurantDTO;
import com.example.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/new/{admin_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> add(@Valid @RequestBody RestaurantDTO dto, @PathVariable Integer admin_id) {
        return new ResponseEntity<>(restaurantService.add(dto, admin_id), HttpStatus.OK);
    }

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> add() {
        return new ResponseEntity<>(restaurantService.getCategories(), HttpStatus.OK);
    }
}
