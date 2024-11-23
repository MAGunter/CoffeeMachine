package com.barista.maker.coffeemachine.controller;

import com.barista.maker.coffeemachine.entity.Drink;
import com.barista.maker.coffeemachine.service.DrinkService;
import com.barista.maker.coffeemachine.service.impl.HolidayServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coffee")
public class CoffeeMachineController {

    private final HolidayServiceImpl holidayService;
    private final DrinkService drinkService;

    @GetMapping("/drinks")
    public ResponseEntity<List<Drink>> getAllDrinks(){
        List<Drink> drinks = drinkService.getAllDrinks();
        return ResponseEntity.ok(drinks);
    }

    @PostMapping("/prepare")
    public ResponseEntity<?> prepareDrink(@RequestBody Drink drink, BindingResult bindingResult,
                                          UriComponentsBuilder uri){
        if(!isWork()){
            return ResponseEntity.badRequest().body("Machine isn't working now");
        }
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Invalid request");
        }
        Drink newDrink = drinkService.prepareDrink(drink.getName(), drink.getRecipe());
        return ResponseEntity.created(uri.path("/coffee/prepare/{id}").build(newDrink.getId()))
                .body(newDrink);
    }

    private boolean isWork(){
        LocalTime time = LocalTime.now();
        DayOfWeek day = LocalDate.now().getDayOfWeek();
        LocalDate date = LocalDate.now();

        return !holidayService.isHoliday(date) && day != DayOfWeek.SUNDAY
                && day != DayOfWeek.SATURDAY
                && time.isAfter(LocalTime.of(8, 0))
                && time.isBefore(LocalTime.of(20, 0));
    }
}
