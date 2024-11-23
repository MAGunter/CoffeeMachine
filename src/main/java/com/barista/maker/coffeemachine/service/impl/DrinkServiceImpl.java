package com.barista.maker.coffeemachine.service.impl;

import com.barista.maker.coffeemachine.entity.Drink;
import com.barista.maker.coffeemachine.repository.DrinkRepository;
import com.barista.maker.coffeemachine.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;

    @Override
    public List<Drink> getAllDrinks() {
        return this.drinkRepository.findAll();
    }

    @Override
    public Drink prepareDrink(String drinkName, Map<String, Integer> recipe) {
        return this.drinkRepository.save(new Drink(drinkName, recipe));
    }

    @Override
    public Drink findByName(String drinkId) {
        return this.drinkRepository.findByName(drinkId);
    }

}
