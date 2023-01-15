package com.skypro.recipebook.service;

import org.springframework.stereotype.Service;

@Service
public interface FileService {
    boolean saveToRecipeFile(String json);

    String readFromRecipeFile();

    String readFromIngredientFile();

    boolean cleanRecipeFile();

    boolean cleanIngredientFile();

    void createEmptyRecipeFileIfNotExists();

    boolean saveToIngredientFile(String json);

    void createEmptyIngredientFileIfNotExists();
}
