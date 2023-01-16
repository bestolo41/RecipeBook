package com.skypro.recipebook.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
}
