package com.nguyenphucthienan.recipeapp.service;

import com.nguyenphucthienan.recipeapp.command.IngredientCommand;
import reactor.core.publisher.Mono;

public interface IngredientService {

    Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    Mono<IngredientCommand> saveIngredientCommand(IngredientCommand ingredientCommand);

    Mono<Void> deleteById(String recipeId, String ingredientId);
}
