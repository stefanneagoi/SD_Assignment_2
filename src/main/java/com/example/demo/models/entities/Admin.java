package com.example.demo.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    public Admin(User u) {
        this.user = u;
    }

    @OneToOne(mappedBy ="admin")
    private Restaurant restaurant;
}
