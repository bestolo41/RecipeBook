package com.skypro.recipebook.model;

import java.util.Objects;

public class Ingredient {
    private String name;
    private int count;
    private String measureUnit;

    public Ingredient(String name, int count, String measureUnit) {
        setName(name);
        setCount(count);
        setMeasureUnit(measureUnit);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank() || name.isEmpty()) {
            throw new RuntimeException("Пустое название ингредиента");
        } else {
            this.name = name;
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count < 0) {
            throw new RuntimeException("Отрицательное значение количества ингредиента");
        } else {
            this.count = count;
        }
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return count == that.count && Objects.equals(name, that.name) && Objects.equals(measureUnit, that.measureUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, count, measureUnit);
    }

    @Override
    public String toString() {
        return "Название: " + getName() + "\n" +
                "Количество: " + getCount() + " " + getMeasureUnit();
    }
}
