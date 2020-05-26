package com.nguyenphucthienan.recipeapp.service;

import com.nguyenphucthienan.recipeapp.command.RecipeCommand;
import com.nguyenphucthienan.recipeapp.converter.RecipeCommandToRecipe;
import com.nguyenphucthienan.recipeapp.converter.RecipeToRecipeCommand;
import com.nguyenphucthienan.recipeapp.domain.Recipe;
import com.nguyenphucthienan.recipeapp.repository.RecipeRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    public static final String NEW_DESCRIPTION = "new description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Transactional
    @Test
    public void testSaveDescription() {
        // Given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe recipe = recipes.iterator().next();
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);

        // When
        Objects.requireNonNull(recipeCommand).setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand).block();

        // Then
        assertEquals(NEW_DESCRIPTION, Objects.requireNonNull(savedRecipeCommand).getDescription());
        assertEquals(recipe.getId(), savedRecipeCommand.getId());
        assertEquals(recipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
        assertEquals(recipe.getCategories().size(), savedRecipeCommand.getCategories().size());
    }
}
