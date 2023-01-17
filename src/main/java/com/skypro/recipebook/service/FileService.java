package com.skypro.recipebook.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

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

    ResponseEntity<InputStreamResource> downloadFile(String name);

    void uploadFile(MultipartFile file) throws IOException;

    Path createTempFile(String suffix);
}
