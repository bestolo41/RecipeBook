package com.skypro.recipebook.controller;

import com.skypro.recipebook.model.AppError;
import com.skypro.recipebook.model.Recipe;
import com.skypro.recipebook.service.impl.RecipeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable int id) {
        try {
            Recipe recipe = recipeService.get(id);
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
                    e.getMessage()),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public String addRecipe(@RequestBody Recipe recipe) {
        return recipeService.add(recipe);
    }
}
