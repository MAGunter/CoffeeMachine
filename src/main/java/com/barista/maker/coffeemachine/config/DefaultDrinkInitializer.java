package com.barista.maker.coffeemachine.config;

import com.barista.maker.coffeemachine.service.DrinkService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class DefaultDrinkInitializer {

    @Bean
    public CommandLineRunner initDefaultDrinks(DrinkService drinkService) {
        return args -> {
            if (drinkService.getAllDrinks().isEmpty()) {
                drinkService.createDrink("Espresso", Map.of(
                        "Coffee Beans", 10
                ));

                drinkService.createDrink("Americano", Map.of(
                        "Coffee Beans", 10,
                        "Water", 100
                ));

                drinkService.createDrink("Cappuccino", Map.of(
                        "Coffee Beans", 10,
                        "Milk", 50
                ));

                System.out.println("Default drinks initialized: Эспрессо, Американо, Капучино");
            }
        };
    }
}

