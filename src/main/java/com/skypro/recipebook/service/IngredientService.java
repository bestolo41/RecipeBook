package com.skypro.recipebook.service;

import com.skypro.recipebook.model.Ingredient;


public interface IngredientService {
        public String add(Ingredient ingredient);

        public Ingredient get(int id);
    }

