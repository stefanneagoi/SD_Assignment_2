package com.example.demo.controller;

import com.example.demo.models.dto.LoginDTO;
import com.example.demo.models.dto.RegisterDTO;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO dto) {
        return new ResponseEntity<>(customerService.addCustomer(dto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO dto) {
        return new ResponseEntity<>(customerService.login(dto), HttpStatus.OK);
    }

    @GetMapping("/orders/{customer_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getOrders(@PathVariable Integer customer_id) {
        return new ResponseEntity<>(orderService.getOrders(customer_id.longValue()), HttpStatus.OK);
    }
}
