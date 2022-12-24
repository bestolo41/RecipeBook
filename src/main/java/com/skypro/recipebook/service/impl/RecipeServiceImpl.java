package com.skypro.recipebook.service.impl;

import com.skypro.recipebook.model.Ingredient;
import com.skypro.recipebook.model.NotFoundException;
import com.skypro.recipebook.model.ReAddingException;
import com.skypro.recipebook.model.Recipe;
import com.skypro.recipebook.service.IngredientService;
import com.skypro.recipebook.service.RecipeService;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static int id = 1;
    private final static Map<Integer, Recipe> recipeBook = new HashMap<>();

    @Override
    public String add(Recipe recipe) {
        for (Recipe value : recipeBook.values()) {
            if (value.getName().equals(recipe.getName())) {
                throw new ReAddingException("Рецепт с таким именем уже существует");
            }
        }
        recipeBook.put(id++, recipe);
        return "Рецепт " + recipe.getName() + " добавлен в книгу";
    }

    @Override
    public Recipe get(int id) {
        if (recipeBook.containsKey(id)) {
            return recipeBook.get(id);
        } else {
            throw new NotFoundException("Рецепта с таким id не существует");
        }
    }

    @Override
    public String edit(int id, Recipe newRecipe) {
        if (recipeBook.containsKey(id)) {
            Recipe oldRecipe = recipeBook.get(id);
            recipeBook.put(id, newRecipe);
            return "Рецепт " + oldRecipe.getName() + " обновлен";
        }
        throw new NotFoundException("Рецепта с таким id не существует");
    }

    @Override
    public String delete(int id) {
        if (recipeBook.containsKey(id)) {
            recipeBook.remove(id);
            return "Рецепт с id " + id + " удален";
        }
        throw new NotFoundException("Рецепта с таким id не существует");
    }

    @Override
    public Set<Recipe> getRecipesByIngredientId(int id) {
        Set<Recipe> recipesContainsIngredientWithId = new HashSet<>();
        String ingredientName;

        if (IngredientServiceImpl.getIngredients().containsKey(id)) {
            ingredientName = IngredientServiceImpl.getIngredients().get(id).getName();
        } else {
            throw new NotFoundException("Ингредиента с таким id не существует");
        }

        for (Recipe recipe : recipeBook.values()) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                if (ingredient.getName().equals(ingredientName)) {
                    recipesContainsIngredientWithId.add(recipe);
                    break;
                }
            }
        }

        if (recipesContainsIngredientWithId.isEmpty()) {
            throw new NotFoundException("Рецептов, содежащих ингредиент с таким id нет");
        }
        return recipesContainsIngredientWithId;
    }

    @Override
    public Set<Recipe> findRecipeByFewIngredients(LinkedList<Integer> ids) {
        Set<Recipe> recipesContainsFewIngredients = new HashSet<>();
        List<Ingredient> ingredientsForSearch = new LinkedList<>();

        for (int i = 0; i < ids.size(); i++) {
            ingredientsForSearch.add(IngredientServiceImpl.getIngredients().get(ids.get(i)));
        }

        for (Recipe recipe : recipeBook.values()) {
            if (recipe.getIngredients().containsAll(ingredientsForSearch)) {
                recipesContainsFewIngredients.add(recipe);
            }
        }

        if (recipesContainsFewIngredients.isEmpty()) {
            throw new NotFoundException("Рецептов с такими ингредиентами нет");
        }
        return recipesContainsFewIngredients;
    }

    @Override
    public List<Recipe> recipesPageByPage(int page) {
        int countOnPage = 10;
        List<Recipe> recipesForResponse = new LinkedList<>();
        Object[] allRecipes = recipeBook.values().toArray();

        for (int i = (page - 1) * countOnPage; i < page * countOnPage - 1; i++) {
            if (i >= allRecipes.length || i < 0) {
                break;
            } else if (allRecipes[i] != null) {
                recipesForResponse.add((Recipe) allRecipes[i]);
            }
        }
        return recipesForResponse;
    }

}
