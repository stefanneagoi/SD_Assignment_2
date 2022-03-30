package com.example.demo.service;

import com.example.demo.models.dto.LoginDTO;
import com.example.demo.models.dto.RegisterDTO;
import com.example.demo.models.entities.Customer;
import com.example.demo.models.entities.User;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public Customer addCustomer(RegisterDTO registerDTO) {
        User u = dtoToUser(registerDTO);
        u.setPasswordDigest(BCrypt.hashpw(u.getPasswordDigest(), BCrypt.gensalt()));
        Customer customer = new Customer(u);
        userRepository.save(u);
        customerRepository.save(customer);
        return customer;
    }

    public Customer login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User Not Found with username:" + loginDTO.getUsername()));
        if(user.getCustomer() == null)
            throw new RuntimeException("User is not a customer!");
        if (BCrypt.checkpw(loginDTO.getPasswordDigest(), user.getPasswordDigest())) {
            Customer customer = user.getCustomer();
            customer.getUser().setCustomer(null);
            return customer;
        }
        else
            throw new RuntimeException("Passwords don't match");
    }

    private User dtoToUser(RegisterDTO dto) {
        return modelMapper.map(dto, User.class);
    }

}
