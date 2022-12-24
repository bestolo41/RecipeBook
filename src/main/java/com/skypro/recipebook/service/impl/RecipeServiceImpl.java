package com.skypro.recipebook.service.impl;

import com.skypro.recipebook.model.NotFoundException;
import com.skypro.recipebook.model.ReAddingException;
import com.skypro.recipebook.model.Recipe;
import com.skypro.recipebook.service.RecipeService;
import org.springframework.stereotype.Service;


import javax.naming.NameNotFoundException;
import java.util.HashMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static int id = 1;
    private final static HashMap<Integer, Recipe> recipeBook = new HashMap<>();

    @Override
    public String add(Recipe recipe) {
        for (Recipe value : recipeBook.values()) {
            if (value.getName().equals(recipe.getName())) {
                throw new ReAddingException("Рецепт с таким именем уже существует");
            }
        }
        recipeBook.put(id++, recipe);
        return "Рецепт " + recipe.getName() + " добавлен в книгу";
    }

    @Override
    public Recipe get(int id) {
        if (recipeBook.containsKey(id)) {
            return recipeBook.get(id);
        } else {
            throw new NotFoundException("Рецепта с таким id не существует");
        }
    }

}
