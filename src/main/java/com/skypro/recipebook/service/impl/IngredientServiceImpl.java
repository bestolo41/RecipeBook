package com.skypro.recipebook.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.recipebook.model.Ingredient;
import com.skypro.recipebook.model.exceptions.NotFoundException;
import com.skypro.recipebook.model.exceptions.ReAddingException;
import com.skypro.recipebook.model.exceptions.ReadingException;
import com.skypro.recipebook.service.FileService;
import com.skypro.recipebook.service.IngredientService;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.HashMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    final private FileService fileService;
    private static int id = 1;
    private static HashMap<Integer, Ingredient> ingredients = new HashMap<>();

    public IngredientServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }

    public static HashMap<Integer, Ingredient> getIngredients() {
        return ingredients;
    }

    @PostConstruct
    void init() {
        readFromIngredientFile();
    }

    @Override
    public String add(Ingredient ingredient) {
        for (Ingredient value : ingredients.values()) {
            if (value.getName().equals(ingredient.getName())) {
                throw new ReAddingException("Ингредиент с таким именем уже существует");
            }
        }
        ingredients.put(id++, ingredient);
        saveToIngredientFile();
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
            saveToIngredientFile();
            return "Ингредиент " + oldIngredient.getName() + " обновлен";
        }
        throw new NotFoundException("Ингредиента с таким id не существует");
    }

    @Override
    public String delete(int id) {
        if (ingredients.containsKey(id)) {
            ingredients.remove(id);
            saveToIngredientFile();
            return "Ингредиент с id " + id + " удален";
        }
        throw new NotFoundException("Ингредиента с таким id не существует");
    }

    private void saveToIngredientFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            fileService.saveToIngredientFile(json);
        } catch (JsonProcessingException e) {
            throw new ReadingException("Не удалось считать данные");
        }
    }

    @Override
    public void readFromIngredientFile() {
        String json = fileService.readFromIngredientFile();
        try {
            ingredients = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new ReadingException("Не удалось считать данные");
        }
    }
}
