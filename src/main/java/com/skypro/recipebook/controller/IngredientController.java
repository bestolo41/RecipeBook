package com.skypro.recipebook.controller;

import com.skypro.recipebook.model.Ingredient;
import com.skypro.recipebook.model.Recipe;
import com.skypro.recipebook.service.IngredientService;
import com.skypro.recipebook.service.impl.IngredientServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    public Object getIngredient(@PathVariable int id) {
        return ingredientService.get(id);
    }

    @PostMapping
    public String addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.add(ingredient);
    }
}
