package com.barista.maker.coffeemachine.repository;

import com.barista.maker.coffeemachine.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Integer> {
    Drink findByName(String name);
}
