package com.skypro.recipebook.service;

import com.skypro.recipebook.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RecipeService {
    private static int id = 1;
    private final static HashMap<Integer, Recipe> recipeBook = new HashMap<>();

    public String addRecipe(Recipe recipe) {
        for (Recipe value : recipeBook.values()) {
            if (value.getName().equals(recipe.getName())) {
                throw new RuntimeException("Рецепт с таким именем уже существует");
            }
        }
        recipeBook.put(id++, recipe);
        return "Рецепт " + recipe.getName() + " добавлен в книгу";
    }

    public String getRecipe(int id) {
        return recipeBook.get(id).toString();
    }
}
