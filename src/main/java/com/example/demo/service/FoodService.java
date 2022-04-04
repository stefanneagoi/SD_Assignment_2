package com.example.demo.service;
import com.example.demo.models.dto.FoodDTO;
import com.example.demo.models.entities.Admin;
import com.example.demo.models.entities.Categories;
import com.example.demo.models.entities.Food;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.FoodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FoodService {
    private final FoodRepository foodRepository;
    private final ModelMapper modelMapper;
    private final AdminRepository adminRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository, ModelMapper modelMapper, AdminRepository adminRepository) {
        this.foodRepository = foodRepository;
        this.modelMapper = modelMapper;
        this.adminRepository = adminRepository;
    }


    public FoodDTO addFood(FoodDTO dto, Long admin_id){
        Food food = dtoToFood(dto);
        food.setCategory(Categories.valueOf(dto.getCategory()));
        Admin admin = adminRepository.findById(admin_id)
                        .orElseThrow(() -> new RuntimeException("Could not find admin!"));
        if(admin.getRestaurant() == null)
            throw new RuntimeException("Restaurant doesn't exist!");
        food.setRestaurant(admin.getRestaurant());
        foodRepository.save(food);
        return dto;
    }

    public Food delete(Long id) {
        Food food = foodRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Food id doesn't exist!"));
        foodRepository.deleteById(id);
        return food;
    }

    public List<FoodDTO> getFoods(Long admin_id) {
        Admin admin = adminRepository.findById(admin_id)
                .orElseThrow(() -> new RuntimeException("Could not find admin!"));
        if(admin.getRestaurant() == null)
            throw new RuntimeException("Restaurant doesn't exist!");
        Set<FoodDTO> dtos = new HashSet<>();
        for( Food food: admin.getRestaurant().getFoods())
            dtos.add(foodToDto(food));
        return new ArrayList<>(dtos);
    }

    private Food dtoToFood(FoodDTO dto){
        return modelMapper.map(dto, Food.class);
    }

    private FoodDTO foodToDto(Food food) {
        return modelMapper.map(food, FoodDTO.class);
    }
}
