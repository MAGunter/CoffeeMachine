package com.barista.maker.coffeemachine.service;

import com.barista.maker.coffeemachine.entity.Drink;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DrinkService {
     List<Drink> getAllDrinks();
     Drink createDrink(String drinkName, Map<String, Integer> recipe);
     Drink findByName(String name);
     String prepareDrink(String drinkName);
     Drink getMostPopularDrink();
}
