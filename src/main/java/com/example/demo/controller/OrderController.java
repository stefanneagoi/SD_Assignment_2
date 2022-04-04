package com.example.demo.controller;
import com.example.demo.models.dto.OrderFoodIdsDTO;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add/{customer_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addOrder(@PathVariable Integer customer_id, @RequestBody OrderFoodIdsDTO orderFoodIdsDTO) {
        return new ResponseEntity<>(orderService.addOrder(customer_id.longValue(), orderFoodIdsDTO.getFoodIds()), HttpStatus.OK);
    }

    @PostMapping("/accept/{order_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> acceptOrder(@PathVariable Integer order_id) {
        return new ResponseEntity<>(orderService.acceptOrder(order_id.longValue()), HttpStatus.OK);
    }

    @PostMapping("/decline/{order_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> declineOrder(@PathVariable Integer order_id) {
        return new ResponseEntity<>(orderService.declineOrder(order_id.longValue()), HttpStatus.OK);
    }

    @PostMapping("/status/{order_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addOrder(@PathVariable Integer order_id) {
        return new ResponseEntity<>(orderService.setStatus(order_id.longValue()), HttpStatus.OK);
    }

}
