package com.skypro.recipebook.service.impl;

import com.skypro.recipebook.model.Recipe;
import com.skypro.recipebook.service.Service;
//import org.springframework.security.acls.model.NotFoundException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.NameNotFoundException;
import java.util.HashMap;

@org.springframework.stereotype.Service
@RestControllerAdvice
public class RecipeServiceImpl implements Service {
    private static int id = 1;
    private final static HashMap<Integer, Recipe> recipeBook = new HashMap<>();

    @Override
    public String add(Object recipe) {
        Recipe recipe1 = (Recipe) recipe;
        for (Recipe value : recipeBook.values()) {
            if (value.getName().equals(recipe1.getName())) {
                throw new RuntimeException("Рецепт с таким именем уже существует");
            }
        }
        recipeBook.put(id++, recipe1);
        return "Рецепт " + recipe1.getName() + " добавлен в книгу";
    }

    @Override
    public Recipe get(int id) throws NameNotFoundException {
        if (recipeBook.containsKey(id)) {
            return recipeBook.get(id);
        } else {
            throw new NameNotFoundException("Рецепта с таким id не существует");
        }
    }

}
