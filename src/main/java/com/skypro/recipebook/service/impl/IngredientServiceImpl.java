package com.skypro.recipebook.service.impl;

import com.skypro.recipebook.model.Ingredient;
import com.skypro.recipebook.model.Recipe;
import com.skypro.recipebook.service.Service;

import java.util.HashMap;

@org.springframework.stereotype.Service
public class IngredientServiceImpl implements Service {
    private static int id = 1;
    private final static HashMap<Integer, Ingredient> ingredients = new HashMap<>();
    @Override
    public String add(Object ingredient) {
        Ingredient ingredient1 = (Ingredient) ingredient;
        for (Ingredient value : ingredients.values()) {
            if (value.getName().equals(ingredient1.getName())) {
                throw new RuntimeException("Ингредиент с таким именем уже существует");
            }
        }
        ingredients.put(id++, ingredient1);
        return "Ингредиент " + ingredient1.getName() + " добавлен в книгу";
    }

    @Override
    public Ingredient get(int id) {
        return ingredients.get(id);
    }
}
