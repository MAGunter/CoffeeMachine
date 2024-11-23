package com.barista.maker.coffeemachine.controller;

import com.barista.maker.coffeemachine.entity.Drink;
import com.barista.maker.coffeemachine.service.DrinkService;
import com.barista.maker.coffeemachine.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/coffee/{productName}")
@RequiredArgsConstructor
public class CoffeeController {
    private final DrinkService drinkService;
    private final HolidayService holidayService;

    @GetMapping
    public Drink getDrink(@ModelAttribute("drink") Drink drink, @PathVariable String productName){
        return this.drinkService.findByName(productName);
    }

    @PostMapping
    public ResponseEntity<?> prepareDrink(@ModelAttribute("drink") Drink drink, @PathVariable String productName){
        if(isWork()){
            return ResponseEntity.badRequest().body("Machine isn't working now");
        }
        return ResponseEntity.ok(this.drinkService.prepareDrink(productName));
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
