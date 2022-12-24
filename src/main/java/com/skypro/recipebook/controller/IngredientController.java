package com.skypro.recipebook.controller;

import com.skypro.recipebook.model.Ingredient;
import com.skypro.recipebook.model.Recipe;
import com.skypro.recipebook.service.IngredientService;
import com.skypro.recipebook.service.impl.IngredientServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int id) {
        return ResponseEntity.ok(ingredientService.get(id));
    }

    @PostMapping
    public ResponseEntity<String> addIngredient(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.add(ingredient));
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> editIngredient(@PathVariable int id, @RequestBody Ingredient newIngredient) {
        return ResponseEntity.ok(ingredientService.edit(id, newIngredient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable int id) {
        return ResponseEntity.ok(ingredientService.delete(id));
    }
}
