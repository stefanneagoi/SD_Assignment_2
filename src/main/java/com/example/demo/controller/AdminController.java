package com.example.demo.controller;
import com.example.demo.models.dto.LoginDTO;
import com.example.demo.models.dto.RegisterDTO;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/admins")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO dto) {
        return new ResponseEntity<>(adminService.login(dto), HttpStatus.OK);
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO dto) {
        return new ResponseEntity<>(adminService.addAdmin(dto), HttpStatus.CREATED);
    }
}
