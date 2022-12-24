package com.skypro.recipebook.service;

import com.skypro.recipebook.model.Ingredient;


public interface IngredientService {
        public String add(Ingredient ingredient);

        public Ingredient get(int id);

    String edit(int id, Ingredient newIngredient);

    String delete(int id);
}

