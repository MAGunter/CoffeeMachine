package com.barista.maker.coffeemachine.service.impl;

import com.barista.maker.coffeemachine.entity.Ingredient;
import com.barista.maker.coffeemachine.repository.IngredientRepository;
import com.barista.maker.coffeemachine.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public List<Ingredient> getAllIngredients() {
        return this.ingredientRepository.findAll();
    }

    @Override
    public void refillIngredients(String name, int quantity) {
        Ingredient ingredient = this.ingredientRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Ingredient not found, please add"));
        ingredient.setStock(ingredient.getStock() + quantity);
        ingredientRepository.save(ingredient);
    }

    @Override
    public void deductIngredients(String name, int quantity) {
        Ingredient ingredient = this.ingredientRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Ingredient not found, please add"));
        if(ingredient.getStock() < quantity){
            throw new NoSuchElementException("Ingredient not available, please refill");
        }
        ingredient.setStock(ingredient.getStock() - quantity);
        ingredientRepository.save(ingredient);
    }

    @Override
    public void addIngredient(String name, int stock) {
        if(this.ingredientRepository.findByName(name).isPresent()){
            throw new NoSuchElementException("Ingredient already exists");
        }
        Ingredient ingredient = new Ingredient(name, stock);
        ingredient.setName(name);
        ingredient.setStock(stock);
        this.ingredientRepository.save(ingredient);
    }
}
