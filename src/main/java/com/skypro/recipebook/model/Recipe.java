package com.skypro.recipebook.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.LinkedList;
import java.util.Objects;
import java.util.TreeMap;
@Getter
@EqualsAndHashCode
public class Recipe {
    private String name;
    private int time;
    private LinkedList<Ingredient> ingredients;
    private LinkedList<String> guide;

    public Recipe(String name, int time, LinkedList<Ingredient> ingredients, LinkedList<String> guide) {
        setName(name);
        setTime(time);
        setIngredients(ingredients);
        setGuide(guide);
    }
    public void setName(String name) {
        if (name == null || name.isBlank() || name.isEmpty()) {
            throw new RuntimeException("Пустое название рецепта");
        } else {
            this.name = name;
        }
    }
    public void setTime(int time) {
        if (time < 0) {
            throw new RuntimeException("Отрицательное значение времени приготовления");
        } else {
            this.time = time;
        }
    }
    public void setIngredients(LinkedList<Ingredient> ingredients) {
        if (ingredients == null) {
            throw new RuntimeException("Ингредиенты отсутствуют");
        } else {
            this.ingredients = ingredients;
        }
    }
    public void setGuide(LinkedList<String> guide) {
        if (guide == null) {
            throw new RuntimeException("Инструкция отсутствуют");
        } else {
            this.guide = guide;
        }
    }

    @Override
    public String toString() {
        return "Название: " + getName() + "\n" +
                "Время приготовления: " + getTime() + " мин.\n"  +
                "Ингредиенты:\n" + getIngredients() +  "\n" +
                "Способ приготовления:\n" + getGuide().toString();
    }
}
