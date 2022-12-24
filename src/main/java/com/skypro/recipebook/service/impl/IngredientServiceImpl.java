package com.skypro.recipebook.service.impl;

import com.skypro.recipebook.model.Ingredient;
import com.skypro.recipebook.model.NotFoundException;
import com.skypro.recipebook.model.ReAddingException;
import com.skypro.recipebook.service.IngredientService;
import org.springframework.stereotype.Service;


import java.util.HashMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static int id = 1;
    private static HashMap<Integer, Ingredient> ingredients = new HashMap<>();

    public static HashMap<Integer, Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public String add(Ingredient ingredient) {
        for (Ingredient value : ingredients.values()) {
            if (value.getName().equals(ingredient.getName())) {
                throw new ReAddingException("Ингредиент с таким именем уже существует");
            }
        }
        ingredients.put(id++, ingredient);
        return "Ингредиент " + ingredient.getName() + " добавлен в книгу";
    }

    @Override
    public Ingredient get(int id) {
        if (ingredients.containsKey(id)) {
            return ingredients.get(id);
        } else {
            throw new NotFoundException("Ингредиента с таким id не существует");
        }
    }

    @Override
    public String edit(int id, Ingredient newIngredient) {
        if (ingredients.containsKey(id)) {
            Ingredient oldIngredient = ingredients.get(id);
            ingredients.put(id, newIngredient);
            return "Ингредиент " + oldIngredient.getName() + " обновлен";
        }
        throw new NotFoundException("Ингредиента с таким id не существует");
    }

    @Override
    public String delete(int id) {
        if (ingredients.containsKey(id)) {
            ingredients.remove(id);
            return "Ингредиент с id " + id + " удален";
        }
        throw new NotFoundException("Ингредиента с таким id не существует");
    }
}
