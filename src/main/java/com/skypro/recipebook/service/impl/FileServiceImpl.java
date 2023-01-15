package com.skypro.recipebook.service.impl;

import com.skypro.recipebook.model.NotFoundException;
import com.skypro.recipebook.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@Service
public class FileServiceImpl implements FileService {
    @Value("${path.to.data.files}")
    private String dataFilesPath;
    @Value("${name.of.recipes.file}")
    private String recipesFileName;
    @Value("${name.of.ingredient.file}")
    private String ingredientsFileName;

    @Override
    public boolean saveToRecipeFile(String json) {
        try {
            Files.writeString(Path.of(dataFilesPath, recipesFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public String readFromRecipeFile() {
        try {
            createEmptyRecipeFileIfNotExists();
            return Files.readString(Path.of(dataFilesPath, recipesFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean cleanRecipeFile() {
        try {
            Files.deleteIfExists(Path.of(dataFilesPath, recipesFileName));
            Files.createFile(Path.of(dataFilesPath, recipesFileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void createEmptyRecipeFileIfNotExists() {
        if (Files.notExists(Path.of(dataFilesPath, recipesFileName))) {
            try {
                Files.createFile(Path.of(dataFilesPath, recipesFileName));
                Files.writeString(Path.of(dataFilesPath, recipesFileName), "{}");
            } catch (IOException e) {
                throw new NotFoundException("Директория не найдена");
            }
        }
    }

    @Override
    public boolean saveToIngredientFile(String json) {
        try {
            Files.writeString(Path.of(dataFilesPath, ingredientsFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFromIngredientFile() {
        try {
            createEmptyIngredientFileIfNotExists();
            return Files.readString(Path.of(dataFilesPath, ingredientsFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean cleanIngredientFile() {
        try {
            Files.deleteIfExists(Path.of(dataFilesPath, ingredientsFileName));
            Files.createFile(Path.of(dataFilesPath, ingredientsFileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void createEmptyIngredientFileIfNotExists() {
        if (Files.notExists(Path.of(dataFilesPath, ingredientsFileName))) {
            try {
                Files.createFile(Path.of(dataFilesPath, ingredientsFileName));
                Files.writeString(Path.of(dataFilesPath, ingredientsFileName), "{}");
            } catch (IOException e) {
                throw new NotFoundException("Директория не найдена");
            }
        }
    }
}
