package com.skypro.recipebook.service;

import com.skypro.recipebook.model.Ingredient;
import com.skypro.recipebook.model.Recipe;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public interface RecipeService {
    public String add(Recipe recipe);

    public Recipe get(int id);

    String edit(int id, Recipe newRecipe);

    String delete(int id);

    Set<Recipe> getRecipesByIngredientId(int id);

    Set<Recipe> findRecipeByFewIngredients(LinkedList<Integer> ids);

    List<Recipe> recipesPageByPage(int page);
}
