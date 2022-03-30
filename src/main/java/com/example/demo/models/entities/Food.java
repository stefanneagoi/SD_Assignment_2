package com.example.demo.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "foods")
@Getter
@Setter
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Size(min = 3, max = 20)
    private String name;

    @Column
    @Size(min = 10, max = 50)
    @NotNull
    private String description;

    @Column(nullable = false)
    @NotNull
    private String price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Categories category;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @NotNull
    private Restaurant restaurant;

    @ManyToMany(mappedBy = "foods")
    private Set<Order> orders;

    public Food(String name, String description, String price, Categories category, Restaurant restaurant) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.restaurant = restaurant;
    }
}
