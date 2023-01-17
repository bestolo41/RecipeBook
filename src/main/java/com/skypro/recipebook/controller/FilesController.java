package com.skypro.recipebook.controller;

import com.skypro.recipebook.service.FileService;
import com.skypro.recipebook.service.IngredientService;
import com.skypro.recipebook.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/files")
@Tag(name = "Файлы", description = "Скачать/загрузить файлы")
public class FilesController {
    private final FileService fileService;
    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @Value("${name.of.recipes.file}")
    private String recipesFileName;

    @Value("${name.of.ingredient.file}")
    private String ingredientsFileName;

    public FilesController(FileService fileService, RecipeService recipeService, RecipeService recipeService1, IngredientService ingredientService) {
        this.fileService = fileService;
        this.recipeService = recipeService1;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/export/recipes")
    @Operation(
            summary = "Скачать файл рецептов",
            description = "Скачивает файл со всеми рецептами"
    )
    private ResponseEntity<InputStreamResource> downloadRecipesFile() {
        return fileService.downloadFile(recipesFileName);
    }

    @GetMapping("/export/recipesBook")
    @Operation(
            summary = "Скачать книгу рецептов"
    )
    private ResponseEntity<Object> downloadRecipesBook() {
        try {
            Path path = recipeService.createRecipeBook();
            if (Files.size(path) == 0) {
                return ResponseEntity.noContent().build();
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(Files.size(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=RecipeBook.txt")
                    .body(resource);
        } catch (
                IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }

    }

    @GetMapping("/export/ingredients")
    @Operation(
            summary = "Скачать файл ингредиентов",
            description = "Скачивает файл со всеми ингредиентами"
    )
    private ResponseEntity<InputStreamResource> downloadIngredientsFile() {

        return fileService.downloadFile(ingredientsFileName);
    }

    @PostMapping(value = "/import/recipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadRecipesFile(@RequestParam MultipartFile file) {
        try {
            fileService.uploadFile(file);
            recipeService.readFromRecipeFile();
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/import/ingredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadIngredientsFile(@RequestParam MultipartFile file) {
        try {
            fileService.uploadFile(file);
            ingredientService.readFromIngredientFile();
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
