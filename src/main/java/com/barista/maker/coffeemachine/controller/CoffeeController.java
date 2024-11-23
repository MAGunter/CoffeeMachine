package com.barista.maker.coffeemachine.controller;

import com.barista.maker.coffeemachine.entity.Drink;
import com.barista.maker.coffeemachine.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coffee/{productId}")
@RequiredArgsConstructor
public class CoffeeController {
    private final DrinkService drinkService;

    @GetMapping
    public Drink getDrink(@ModelAttribute("drink") Drink drink, @PathVariable String productId){
        return this.drinkService.findByName(productId);
    }

}
