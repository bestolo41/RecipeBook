package com.skypro.recipebook.controller;

import com.skypro.recipebook.model.AppError;
import com.skypro.recipebook.model.Ingredient;
import com.skypro.recipebook.model.Recipe;
import com.skypro.recipebook.service.RecipeService;
import com.skypro.recipebook.service.impl.RecipeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id) {
        return ResponseEntity.ok(recipeService.get(id));

    }

    @PostMapping
    public ResponseEntity<String> addRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.add(recipe));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editRecipe(@PathVariable int id, @RequestBody Recipe newRecipe) {
        return ResponseEntity.ok(recipeService.edit(id, newRecipe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable int id) {
        return ResponseEntity.ok(recipeService.delete(id));
    }

    @GetMapping("/contain/{id}")
    public ResponseEntity<HashSet<Recipe>> getRecipesByIngredientId(@PathVariable int id) {
        return ResponseEntity.ok(recipeService.getRecipesByIngredientId(id));
    }

    @PostMapping("/contain")
    public ResponseEntity<HashSet<Recipe>> findRecipeByFewIngredients(@RequestBody LinkedList<Ingredient> ingredients) {
        return ResponseEntity.ok(recipeService.findRecipeByFewIngredients(ingredients));
    }

    @GetMapping("/all")
    public ResponseEntity<LinkedList<Recipe>> getAllRecipesPageByPage(@RequestParam int page) {
        return ResponseEntity.ok(recipeService.recipesPageByPage(page));
    }
}
