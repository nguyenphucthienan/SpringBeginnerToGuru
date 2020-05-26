package com.nguyenphucthienan.recipeapp.service;

import com.nguyenphucthienan.recipeapp.command.IngredientCommand;
import com.nguyenphucthienan.recipeapp.converter.IngredientCommandToIngredient;
import com.nguyenphucthienan.recipeapp.converter.IngredientToIngredientCommand;
import com.nguyenphucthienan.recipeapp.domain.Ingredient;
import com.nguyenphucthienan.recipeapp.domain.Recipe;
import com.nguyenphucthienan.recipeapp.domain.UnitOfMeasure;
import com.nguyenphucthienan.recipeapp.repository.reactive.RecipeReactiveRepository;
import com.nguyenphucthienan.recipeapp.repository.reactive.UnitOfMeasureReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeReactiveRepository recipeReactiveRepository;
    private final UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(RecipeReactiveRepository recipeReactiveRepository,
                                 UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository,
                                 IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeReactiveRepository = recipeReactiveRepository;
        this.unitOfMeasureReactiveRepository = unitOfMeasureReactiveRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId) {
        return recipeReactiveRepository
                .findById(recipeId)
                .flatMapIterable(Recipe::getIngredients)
                .filter(ingredient -> ingredient.getId().equalsIgnoreCase(ingredientId))
                .single()
                .map(ingredient -> {
                    IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredient);
                    Objects.requireNonNull(ingredientCommand).setRecipeId(recipeId);
                    return ingredientCommand;
                });
    }

    @Override
    public Mono<IngredientCommand> saveIngredientCommand(IngredientCommand ingredientCommand) {
        Recipe recipe = recipeReactiveRepository.findById(ingredientCommand.getRecipeId()).block();

        if (recipe == null) {
            // TODO Implement error handling
            log.error("Recipe id not found: " + ingredientCommand.getRecipeId());
            return Mono.just(new IngredientCommand());
        }

        Optional<Ingredient> ingredientOptional = recipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                .findFirst();

        if (!ingredientOptional.isPresent()) {
            recipe.addIngredient(ingredientCommandToIngredient.convert(ingredientCommand));
        } else {
            Ingredient ingredientFound = ingredientOptional.get();
            ingredientFound.setDescription(ingredientCommand.getDescription());
            ingredientFound.setAmount(ingredientCommand.getAmount());
            UnitOfMeasure unitOfMeasure = unitOfMeasureReactiveRepository.findById(ingredientCommand.getUnitOfMeasure().getId()).block();
            ingredientFound.setUnitOfMeasure(unitOfMeasure);
            if (unitOfMeasure == null) {
                throw new RuntimeException("Unit of Measure not found");
            }
        }

        Recipe savedRecipe = recipeReactiveRepository.save(recipe).block();

        Optional<Ingredient> savedIngredientOptional = Objects.requireNonNull(savedRecipe).getIngredients()
                .stream()
                .filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientCommand.getId()))
                .findFirst();

        // Check by description
        if (!savedIngredientOptional.isPresent()) {
            savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getDescription().equals(ingredientCommand.getDescription()))
                    .filter(recipeIngredients -> recipeIngredients.getAmount().equals(ingredientCommand.getAmount()))
                    .filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure().getId().equals(ingredientCommand.getUnitOfMeasure().getId()))
                    .findFirst();
        }

        // TODO Check for fail
        IngredientCommand ingredientCommandSaved = ingredientToIngredientCommand.convert(savedIngredientOptional.get());
        Objects.requireNonNull(ingredientCommandSaved).setRecipeId(recipe.getId());

        return Mono.just(ingredientCommandSaved);
    }

    @Override
    public Mono<Void> deleteById(String recipeId, String ingredientId) {
        log.debug("Deleting ingredient - recipe id: " + recipeId + " - ingredient id: " + ingredientId);

        Recipe recipe = recipeReactiveRepository.findById(recipeId).block();

        if (recipe == null) {
            // TODO Implement error handling
            log.error("Recipe id not found: " + recipeId);
        }

        Optional<Ingredient> ingredientOptional = Objects.requireNonNull(recipe)
                .getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .findFirst();

        if (!ingredientOptional.isPresent()) {
            // TODO Implement error handling
            log.error("Ingredient id not found: " + ingredientId);
        }

        recipe.getIngredients().remove(ingredientOptional.get());
        recipeReactiveRepository.save(recipe).block();
        return Mono.empty();
    }
}
