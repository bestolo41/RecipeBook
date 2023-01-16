package com.skypro.recipebook.service;

import com.skypro.recipebook.model.Ingredient;
import com.skypro.recipebook.model.Recipe;

import java.util.HashSet;
import java.util.LinkedList;

public interface RecipeService {
    public String add(Recipe recipe);

    public Recipe get(int id);

    String edit(int id, Recipe newRecipe);

    String delete(int id);

    HashSet<Recipe> getRecipesByIngredientId(int id);

    HashSet<Recipe> findRecipeByFewIngredients(LinkedList<Ingredient> ingredients);

    LinkedList<Recipe> recipesPageByPage(int page);

    void saveToRecipeFile();

    void readFromRecipeFile();
}
