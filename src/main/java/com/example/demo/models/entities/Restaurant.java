package com.example.demo.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Size(min = 6)
    private String name;

    @Column
    private String location;

    @Column
    private String deliveryZones;

    @OneToOne
    @JoinColumn(name="admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "restaurant")
    private Set<Food> foods;

    public Restaurant(String name, String location, String deliveryZones, Admin admin) {
        this.name = name;
        this.location = location;
        this.deliveryZones = deliveryZones;
        this.admin = admin;
    }
}
