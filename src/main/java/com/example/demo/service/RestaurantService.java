package com.example.demo.service;
import com.example.demo.models.dto.RestaurantDTO;
import com.example.demo.models.entities.Admin;
import com.example.demo.models.entities.Categories;
import com.example.demo.models.entities.Restaurant;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;
    private final AdminRepository adminRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, ModelMapper modelMapper, AdminRepository adminRepository) {
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
        this.adminRepository = adminRepository;
    }

    public Restaurant add(RestaurantDTO restaurantDTO, Integer admin_id) {
        Admin admin = adminRepository.findById(admin_id.longValue())
                .orElseThrow(()-> new RuntimeException("Admin doesn't exist"));
        Restaurant restaurant = dtoToRestaurant(restaurantDTO);
        restaurant.setAdmin(admin);
        restaurantRepository.save(restaurant);
        restaurant.getAdmin().setRestaurant(null);
        return restaurant;
    }

    public List<Categories> getCategories(){
        return Arrays.asList(Categories.values());
    }
    private Restaurant dtoToRestaurant(RestaurantDTO dto){
        return modelMapper.map(dto, Restaurant.class);
    }
}
