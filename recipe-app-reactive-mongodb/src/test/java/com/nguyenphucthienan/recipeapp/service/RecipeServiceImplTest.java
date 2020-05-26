package com.nguyenphucthienan.recipeapp.service;

import com.nguyenphucthienan.recipeapp.command.RecipeCommand;
import com.nguyenphucthienan.recipeapp.converter.RecipeCommandToRecipe;
import com.nguyenphucthienan.recipeapp.converter.RecipeToRecipeCommand;
import com.nguyenphucthienan.recipeapp.domain.Recipe;
import com.nguyenphucthienan.recipeapp.repository.reactive.RecipeReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeReactiveRepository recipeReactiveRepository;

    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeReactiveRepository, recipeToRecipeCommand, recipeCommandToRecipe);
    }

    @Test
    public void getRecipesTest() {
        Recipe recipe = new Recipe();
        recipe.setId("1");

        when(recipeService.getRecipes()).thenReturn(Flux.just(recipe));
        List<Recipe> recipes = recipeService.getRecipes().collectList().block();

        assertEquals(Objects.requireNonNull(recipes).size(), 1);
        verify(recipeReactiveRepository, times(1)).findAll();
    }

    @Test
    public void getRecipeByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId("1");

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));
        Recipe recipeReturned = recipeService.findById("1").block();

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, never()).findAll();
    }

    @Test
    public void getRecipeCommandByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId("1");

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId("1");

        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

        RecipeCommand commandById = recipeService.findCommandById("1").block();

        assertNotNull(commandById);
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, never()).findAll();
    }

    @Test
    public void testDeleteById() {
        when(recipeReactiveRepository.deleteById(anyString())).thenReturn(Mono.empty());

        String id = "1";
        recipeService.deleteById(id).block();

        verify(recipeReactiveRepository, times(1)).deleteById(anyString());
    }
}
