package com.example.demo.service;
import com.example.demo.models.dto.FoodDTO;
import com.example.demo.models.dto.OrderDTO;
import com.example.demo.models.entities.Customer;
import com.example.demo.models.entities.Food;
import com.example.demo.models.entities.Order;
import com.example.demo.models.entities.Status;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.OrderRepository;
import jdk.jshell.Snippet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderService {
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public OrderService(ModelMapper modelMapper, OrderRepository orderRepository, CustomerRepository customerRepository, FoodRepository foodRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.foodRepository = foodRepository;
    }

    public OrderDTO addOrder(Long customer_id, List<Integer> food_ids) {
        Order order = new Order();
        order.setFoods(new HashSet<>());
        Customer customer = customerRepository.findById(customer_id)
                .orElseThrow(() -> new RuntimeException("Customer does not exist!"));
        order.setCustomer(customer);

        for(Integer food_id : food_ids) {
            Food food = foodRepository.findById(food_id.longValue())
                    .orElseThrow(() -> new RuntimeException("Food not found"));
            order.addFood(food);
        }
        orderRepository.save(order);
        return orderToDto(order);
    }

    public List<OrderDTO> getOrders(Long customer_id) {
        Customer customer = customerRepository.findById(customer_id)
                .orElseThrow(() -> new RuntimeException("Customer does not exist!"));
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for(Order order : customer.getOrders())
            orderDTOList.add(orderToDto(order));
        return orderDTOList;
    }

    public OrderDTO setStatus(Long order_id) {
        Order order = orderRepository.findById(order_id)
                .orElseThrow(() -> new RuntimeException("Cannot find order"));
        if(order.getStatus() == Status.Declined)
            throw new RuntimeException("Order is declined");
        else if(order.getStatus() == Status.Delivered)
            throw new RuntimeException("Order is delivered");
        switch (order.getStatus()) {
            case Accepted -> order.setStatus(Status.InDelivery);
            case InDelivery -> order.setStatus(Status.Delivered);
        }
        orderRepository.save(order);
        return orderToDto(order);
    }

    public OrderDTO acceptOrder(Long order_id) {
        Order order = orderRepository.findById(order_id)
                .orElseThrow(() -> new RuntimeException("Cannot find order"));
        if(order.getStatus() != Status.Pending)
            throw new RuntimeException("Order status already set!");
        order.setStatus(Status.Accepted);
        orderRepository.save(order);
        return orderToDto(order);
    }

    public OrderDTO declineOrder(Long order_id) {
        Order order = orderRepository.findById(order_id)
                .orElseThrow(() -> new RuntimeException("Cannot find order"));
        if(order.getStatus() != Status.Pending)
            throw new RuntimeException("Order status already set!");
        order.setStatus(Status.Declined);
        orderRepository.save(order);
        return orderToDto(order);
    }

    private OrderDTO orderToDto(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getId().intValue());
        dto.setCustomerId(order.getCustomer().getId().intValue());
        dto.setStatus(order.getStatus().toString());
        if(order.getFoods() != null) {
            List<FoodDTO> foodDTOS = new ArrayList<>();
            for (Food food : order.getFoods())
                foodDTOS.add(foodToDto(food));
            dto.setFoods(foodDTOS);
        }
        return dto;
    }

    private FoodDTO foodToDto(Food food) {
        FoodDTO dto = modelMapper.map(food, FoodDTO.class);
        dto.setId(food.getId().intValue());
        return dto;
    }
}
