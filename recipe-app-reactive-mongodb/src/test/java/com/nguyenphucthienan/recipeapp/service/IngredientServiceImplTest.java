package com.nguyenphucthienan.recipeapp.service;

import com.nguyenphucthienan.recipeapp.command.IngredientCommand;
import com.nguyenphucthienan.recipeapp.converter.IngredientCommandToIngredient;
import com.nguyenphucthienan.recipeapp.converter.IngredientToIngredientCommand;
import com.nguyenphucthienan.recipeapp.converter.UnitOfMeasureCommandToUnitOfMeasure;
import com.nguyenphucthienan.recipeapp.converter.UnitOfMeasureToUnitOfMeasureCommand;
import com.nguyenphucthienan.recipeapp.domain.Ingredient;
import com.nguyenphucthienan.recipeapp.domain.Recipe;
import com.nguyenphucthienan.recipeapp.repository.reactive.RecipeReactiveRepository;
import com.nguyenphucthienan.recipeapp.repository.reactive.UnitOfMeasureReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    RecipeReactiveRepository recipeReactiveRepository;

    @Mock
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    IngredientService ingredientService;

    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(recipeReactiveRepository, unitOfMeasureReactiveRepository,
                ingredientToIngredientCommand, ingredientCommandToIngredient);
    }

    @Test
    public void findByRecipeIdAndId() {
    }

    @Test
    public void findByRecipeIdAndRecipeIdHappyPath() {
        Recipe recipe = new Recipe();
        recipe.setId("1");

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId("1");
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId("2");
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId("3");

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId("1", "3").block();

        assertEquals("3", Objects.requireNonNull(ingredientCommand).getId());
        verify(recipeReactiveRepository, times(1)).findById(anyString());
    }

    @Test
    public void testSaveRecipeCommand() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId("2");
        ingredientCommand.setRecipeId("1");

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId("2");

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(new Recipe()));
        when(recipeReactiveRepository.save(any())).thenReturn(Mono.just(savedRecipe));

        IngredientCommand savedIngredientCommand = ingredientService.saveIngredientCommand(ingredientCommand).block();

        assertEquals("2", Objects.requireNonNull(savedIngredientCommand).getId());
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    public void testDeleteById() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId("2");
        Recipe recipe = new Recipe();
        recipe.addIngredient(ingredient);

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));
        when(recipeReactiveRepository.save(any())).thenReturn(Mono.just(recipe));

        ingredientService.deleteById("1", "2").block();

        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, times(1)).save(any(Recipe.class));
    }
}
