package com.skypro.recipebook.service.impl;

import com.skypro.recipebook.model.exceptions.FileCreationException;
import com.skypro.recipebook.model.exceptions.NotFoundException;
import com.skypro.recipebook.model.exceptions.ReadingException;
import com.skypro.recipebook.service.FileService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Getter
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
            throw new ReadingException("Не удалось считать данные");
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
            throw new ReadingException("Не удалось считать данные");
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

    @Override
    public ResponseEntity<InputStreamResource> downloadFile(String name) {
        File file = new File(dataFilesPath + "/" + name);

        try {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name)
                    .body(resource);
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        Path filePath = Path.of(dataFilesPath,file.getOriginalFilename());
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (
                InputStream is = file.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
    }

    @Override
    public Path createTempFile(String suffix) {
        try {
            return Files.createTempFile(Path.of(dataFilesPath), "tempFile", suffix);
        } catch (IOException e) {
            throw new FileCreationException("Не удалось создать временный файл");
        }
    }
}
