package com.barista.maker.coffeemachine.controller;


import com.barista.maker.coffeemachine.entity.Ingredient;
import com.barista.maker.coffeemachine.entity.RefillIngredient;
import com.barista.maker.coffeemachine.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ingredients")
@Tag(name = "Ingredient API", description = "Operations related to ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    @Operation(summary = "Show list of ingredients", description = "finds all ingredients in machine")
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @PostMapping("/add")
    @Operation(summary = "add ingredients", description = "add ingredients to machine if it's not exist")
    public ResponseEntity<?> addIngredient(@RequestBody RefillIngredient add,
                                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Invalid request");
        }
        try {
            String name = add.getName();
            int quantity = add.getQuantity();
            this.ingredientService.addIngredient(name, quantity);
            return ResponseEntity.ok("Ingredient added, all okay");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PatchMapping("/refill")
    @Operation(summary = "refill ingredients", description = "fill machine to exist ingredients")
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
