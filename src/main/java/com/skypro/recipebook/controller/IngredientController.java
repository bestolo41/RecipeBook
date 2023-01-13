package com.skypro.recipebook.controller;

import com.skypro.recipebook.model.Ingredient;
import com.skypro.recipebook.model.Recipe;
import com.skypro.recipebook.service.IngredientService;
import com.skypro.recipebook.service.impl.IngredientServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
@Tag(name = "Ингредиенты", description = "Операции над ингредиентами")
public class IngredientController {

    IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить ингредиент по id",
            description = "Введите номер id ингредиента чтобы получить его"
    )
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int id) {
        return ResponseEntity.ok(ingredientService.get(id));
    }

    @PostMapping
    @Operation(
            summary = "Добавить новый ингредиент",
            description = "Укажите новый интредиент в формате JSON чтобы добавить его"
    )
    public ResponseEntity<String> addIngredient(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.add(ingredient));
    }
    @PutMapping("/{id}")
    @Operation(
            summary = "Изменить ингредиент по id",
            description = "Введите номер id ингредиента чтобы изменить его. Введите новые поля в формате JSON"
    )
    public ResponseEntity<String> editIngredient(@PathVariable int id, @RequestBody Ingredient newIngredient) {
        return ResponseEntity.ok(ingredientService.edit(id, newIngredient));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить ингредиент по id",
            description = "Введите номер id ингредиента чтобы удалить его"
    )
    public ResponseEntity<String> deleteIngredient(@PathVariable int id) {
        return ResponseEntity.ok(ingredientService.delete(id));
    }
}
