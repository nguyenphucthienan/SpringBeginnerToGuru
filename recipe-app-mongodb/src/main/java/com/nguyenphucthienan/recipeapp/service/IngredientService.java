package com.nguyenphucthienan.recipeapp.service;

import com.nguyenphucthienan.recipeapp.command.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteById(String recipeId, String ingredientId);
}
