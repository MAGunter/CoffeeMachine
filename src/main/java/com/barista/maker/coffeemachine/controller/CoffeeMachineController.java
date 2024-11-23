package com.barista.maker.coffeemachine.controller;

import com.barista.maker.coffeemachine.entity.Drink;
import com.barista.maker.coffeemachine.repository.DrinkRepository;
import com.barista.maker.coffeemachine.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coffee")
public class CoffeeMachineController {

    private final DrinkService drinkService;
    private final DrinkRepository drinkRepository;

    @GetMapping("/drinks")
    public ResponseEntity<List<Drink>> getAllDrinks(){
        List<Drink> drinks = drinkService.getAllDrinks();
        return ResponseEntity.ok(drinks);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDrink(@RequestBody Drink drink, BindingResult bindingResult,
                                          UriComponentsBuilder uri){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Invalid request");
        }
        Drink newDrink = this.drinkService.createDrink(drink.getName(), drink.getRecipe());
        newDrink.setCustom(true);
        drinkRepository.save(newDrink);
        return ResponseEntity.created(uri.path("/coffee/prepare/{id}").build(newDrink.getId()))
                .body(newDrink);
    }

}
