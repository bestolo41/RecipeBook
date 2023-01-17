package com.skypro.recipebook.controller;

import com.skypro.recipebook.model.Ingredient;
import com.skypro.recipebook.model.Recipe;
import com.skypro.recipebook.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/recipes")
@Tag(name = "Рецепты", description = "Операции над рецептами")
public class RecipeController {
    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить рецепт по id",
            description = "Введите номер id рецепта чтобы получить его"
    )
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id) {
        return ResponseEntity.ok(recipeService.get(id));

    }

    @PostMapping
    @Operation(
            summary = "Добавить рецепт",
            description = "Добавить новый рецепт в книгу, указав его в теле в формате JSON"
    )
    public ResponseEntity<String> addRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.add(recipe));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменить рецепт по id",
            description = "Введите номер id рецепта чтобы изменить его. Введите новые поля в формате JSON"
    )
    public ResponseEntity<String> editRecipe(@PathVariable int id, @RequestBody Recipe newRecipe) {
        return ResponseEntity.ok(recipeService.edit(id, newRecipe));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить рецепт по id",
            description = "Введите номер id рецепта чтобы удалить его"
    )
    public ResponseEntity<String> deleteRecipe(@PathVariable int id) {
        return ResponseEntity.ok(recipeService.delete(id));
    }

    @GetMapping("/contain/{id}")
    @Operation(
            summary = "Получить рецепты по id ингредиента",
            description = "Введите номер id ингредиента чтобы получить все рецепты его содержащие"
    )
    public ResponseEntity<HashSet<Recipe>> getRecipesByIngredientId(@PathVariable int id) {
        return ResponseEntity.ok(recipeService.getRecipesByIngredientId(id));
    }

    @PostMapping("/contain")
    @Operation(
            summary = "Получить рецепты по нескольким ингредиентам",
            description = "Введите один или несколько ингредиентов в формате JSON чтобы получить рецепты их содержащие"
    )
    public ResponseEntity<HashSet<Recipe>> findRecipeByFewIngredients(@RequestBody LinkedList<Ingredient> ingredients) {
        return ResponseEntity.ok(recipeService.findRecipeByFewIngredients(ingredients));
    }

    @GetMapping("/all")
    @Operation(
            summary = "Получить все рецепты постранично",
            description = "Укажите номер страницы, чтобы получить рецепты из этой страницы"
    )
    public ResponseEntity<List<Recipe>> getAllRecipesPageByPage(@RequestParam int page) {
        return ResponseEntity.ok(recipeService.recipesPageByPage(page));
    }
}
