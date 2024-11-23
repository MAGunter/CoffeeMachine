package com.barista.maker.coffeemachine.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private final String name;

    @Column(nullable = false)
    private boolean isCustom = false;

    @ElementCollection
    @CollectionTable(name = "drink_recipe", joinColumns = @JoinColumn(name = "drink_id"))
    @MapKeyColumn(name = "ingredient_name")
    @Column(name = "quantity")
    private final Map<String, Integer> recipe;

    @Column(nullable = false)
    private boolean status;

}

