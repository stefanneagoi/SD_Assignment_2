package com.example.demo.service;
import com.example.demo.models.entities.Customer;
import com.example.demo.models.entities.Order;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(ModelMapper modelMapper, OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public Order addOrder(Long customer_id) {
        Order order = new Order();
        Customer customer = customerRepository.findById(customer_id)
                .orElseThrow(() -> new RuntimeException("Customer does not exist!"));
        order.setCustomer(customer);
        orderRepository.save(order);
        order.setCustomer(null);
        return order;
    }
}
