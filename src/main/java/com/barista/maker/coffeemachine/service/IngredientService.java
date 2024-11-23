package com.barista.maker.coffeemachine.service;

import com.barista.maker.coffeemachine.entity.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IngredientService {
    List<Ingredient> getAllIngredients();
    void refillIngredients(String name, int quantity);
    void deductIngredients(String name, int quantity);
    void addIngredient(String name, int stock);
}
