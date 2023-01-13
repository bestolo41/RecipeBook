package com.skypro.recipebook.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Getter
@EqualsAndHashCode
public class Ingredient {
    private String name;
    private int count;
    private String measureUnit;

    public Ingredient(String name, int count, String measureUnit) {
        setName(name);
        setCount(count);
        setMeasureUnit(measureUnit);
    }

    public void setName(String name) {
        if (name == null || name.isBlank() || name.isEmpty()) {
            throw new RuntimeException("Пустое название ингредиента");
        } else {
            this.name = name;
        }
    }

    public void setCount(int count) {
        if (count < 0) {
            throw new RuntimeException("Отрицательное значение количества ингредиента");
        } else {
            this.count = count;
        }
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    @Override
    public String toString() {
        return "Название: " + getName() + "\n" +
                "Количество: " + getCount() + " " + getMeasureUnit();
    }
}
