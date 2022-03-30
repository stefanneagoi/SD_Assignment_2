package com.example.demo.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Size(min = 6, max = 16)
    private String username;

    @Column(nullable = false)
    private String passwordDigest;

    @OneToOne(mappedBy ="user")
    private Customer customer;

    @OneToOne(mappedBy ="user")
    private Admin admin;

    public User(String username, String passwordDigest) {
        this.username = username;
        this.passwordDigest = passwordDigest;
    }
}
