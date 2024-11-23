package com.barista.maker.coffeemachine.controller;

import com.barista.maker.coffeemachine.entity.Drink;
import com.barista.maker.coffeemachine.repository.DrinkRepository;
import com.barista.maker.coffeemachine.service.DrinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coffee")
@Tag(name = "Coffee Machine API", description = "Operations related to manage all coffees")
public class CoffeeMachineController {

    private final DrinkService drinkService;
    private final DrinkRepository drinkRepository;

    @GetMapping("/drinks")
    @Operation(summary = "Show list of drinks", description = "finds all drinks in menu")
    public ResponseEntity<List<Drink>> getAllDrinks(){
        List<Drink> drinks = drinkService.getAllDrinks();
        return ResponseEntity.ok(drinks);
    }

    @PostMapping("/create")
    @Operation(summary = "create a drink",
            description = "create a drink with name and recipe, and set custom true")
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

    @GetMapping("/most-popular")
    @Operation(summary = "Get the most popular drink", description = "Finds the drink with the highest preparation count")
    public ResponseEntity<?> getMostPopularDrink() {
        try {
            Drink mostPopularDrink = drinkService.getMostPopularDrink();
            return ResponseEntity.ok(mostPopularDrink);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
