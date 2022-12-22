package com.skypro.recipebook.controller;

import com.skypro.recipebook.model.AppError;
import com.skypro.recipebook.model.Recipe;
import com.skypro.recipebook.service.RecipeService;
import com.skypro.recipebook.service.impl.RecipeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        return recipeService.get(id);
    }

    @PostMapping
    public String addRecipe(@RequestBody Recipe recipe) {
        return recipeService.add(recipe);
    }
}
