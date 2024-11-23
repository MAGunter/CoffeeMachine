package com.barista.maker.coffeemachine.service.impl;

import com.barista.maker.coffeemachine.entity.Drink;
import com.barista.maker.coffeemachine.repository.DrinkRepository;
import com.barista.maker.coffeemachine.service.DrinkService;
import com.barista.maker.coffeemachine.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;
    private final IngredientService ingredientService;

    @Override
    public List<Drink> getAllDrinks() {
        return this.drinkRepository.findAll();
    }

    @Override
    public Drink createDrink(String drinkName, Map<String, Integer> recipe) {
        recipe.forEach((ingredientName, quantity) -> {
            ingredientService.deductIngredients(ingredientName, quantity);
        });
        return this.drinkRepository.save(new Drink(drinkName, recipe));
    }

    @Override
    public Drink findByName(String drinkId) {
        return this.drinkRepository.findByName(drinkId);
    }

    @Override
    public String prepareDrink(String drinkName) {
        Drink drink = drinkRepository.findByName(drinkName);
        drink.setStatus(true);
        drinkRepository.save(drink);
        return "Status: " + drink.isStatus();
    }

}
