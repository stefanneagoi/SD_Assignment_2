package com.example.demo.controller;

import com.example.demo.models.dto.FoodDTO;
import com.example.demo.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/foods")
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/add/{admin_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> add(@Valid @RequestBody FoodDTO dto, @PathVariable Integer admin_id) {
        return new ResponseEntity<>(foodService.addFood(dto, admin_id.longValue()), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{food_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> remove(@PathVariable Integer food_id) {
        return new ResponseEntity<>(foodService.delete(food_id.longValue()), HttpStatus.OK);
    }

    @GetMapping("/{admin_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getFoods(@PathVariable Integer admin_id) {
        return new ResponseEntity<>(foodService.getFoods(admin_id.longValue()), HttpStatus.OK);
    }


}
