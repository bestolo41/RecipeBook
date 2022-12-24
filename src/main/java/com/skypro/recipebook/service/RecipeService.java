package com.skypro.recipebook.service;

import com.skypro.recipebook.model.Recipe;

public interface RecipeService {
    public String add(Recipe recipe);

    public Recipe get(int id);
}
