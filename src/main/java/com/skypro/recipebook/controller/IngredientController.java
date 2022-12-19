package com.skypro.recipebook.controller;

import com.skypro.recipebook.model.Ingredient;
import com.skypro.recipebook.model.Recipe;
import com.skypro.recipebook.service.impl.IngredientServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    IngredientServiceImpl ingredientService;

    public IngredientController(IngredientServiceImpl ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/get_ingredient/{id}")
    public Object getIngredient(@PathVariable int id) {
        return ingredientService.get(id);
    }

    @PostMapping("/add_ingredient")
    public String addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.add(ingredient);
    }
}
