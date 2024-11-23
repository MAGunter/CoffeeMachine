package com.barista.maker.coffeemachine.controller;

import com.barista.maker.coffeemachine.entity.Drink;
import com.barista.maker.coffeemachine.service.DrinkService;
import com.barista.maker.coffeemachine.service.HolidayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/coffee/{drinkName}")
@RequiredArgsConstructor
@Tag(name = "Coffee API", description = "Operations related to making coffee")
public class CoffeeController {
    private final DrinkService drinkService;
    private final HolidayService holidayService;

    @GetMapping
    @Operation(summary = "Get drink by name", description = "find drink by name")
    public Drink getDrink(@ModelAttribute("drink") Drink drink, @PathVariable String drinkName){
        return this.drinkService.findByName(drinkName);
    }

    @PostMapping
    @Operation(summary = "prepare drink",
            description = "if machine is working it's prepare drink, then it find drink by name and set status true" +
                    "if machine isn't working return bad request" +
                    "and uses ingredients to prepare drink like milk or coffee beans")
    public ResponseEntity<?> prepareDrink(@ModelAttribute("drink") Drink drink, @PathVariable String drinkName){
        if(isWork()){
            return ResponseEntity.badRequest().body("Machine isn't working now");
        }
        return ResponseEntity.ok(this.drinkService.prepareDrink(drinkName));
    }

    private boolean isWork(){
        LocalTime time = LocalTime.now();
        DayOfWeek day = LocalDate.now().getDayOfWeek();
        LocalDate date = LocalDate.now();

        return !holidayService.isHoliday(date) && day != DayOfWeek.SUNDAY
                && day != DayOfWeek.SATURDAY
                && time.isAfter(LocalTime.of(8, 0))
                && time.isBefore(LocalTime.of(17, 0));
    }

}
