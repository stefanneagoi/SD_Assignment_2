package com.example.demo.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

    public Customer(User u) {
        this.user = u;
    }
}
