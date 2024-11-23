package com.barista.maker.coffeemachine.controller;


import com.barista.maker.coffeemachine.entity.Ingredient;
import com.barista.maker.coffeemachine.entity.RefillIngredient;
import com.barista.maker.coffeemachine.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addIngredient(@RequestBody RefillIngredient add,
                                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Invalid request");
        }
        try {
            String name = add.getName();
            int quantity = add.getQuantity();
            this.ingredientService.addIngredient(name, quantity);
            return ResponseEntity.ok("Ingredient refilled, all okay");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PatchMapping("/refill")
    public ResponseEntity<?> refillIngredient(@RequestBody RefillIngredient refill,
                                              BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Invalid request");
        }
        try {
            String name = refill.getName();
            int quantity = refill.getQuantity();
            this.ingredientService.refillIngredients(name, quantity);
            return ResponseEntity.ok("Ingredient refilled, all okay");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
