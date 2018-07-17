package com.nguyenphucthienan.recipeapp.converter;

import com.nguyenphucthienan.recipeapp.command.CategoryCommand;
import com.nguyenphucthienan.recipeapp.command.IngredientCommand;
import com.nguyenphucthienan.recipeapp.command.NotesCommand;
import com.nguyenphucthienan.recipeapp.command.RecipeCommand;
import com.nguyenphucthienan.recipeapp.domain.Difficulty;
import com.nguyenphucthienan.recipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {
    public static final Long ID = 1L;
    public static final String DESCRIPTION = "description";
    public static final Integer PREP_TIME = 6;
    public static final Integer COOK_TIME = 8;
    public static final Integer SERVINGS = 3;
    public static final String SOURCE = "source";
    public static final String URL = "url";
    public static final String DIRECTIONS = "directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Long NOTES_ID = 1L;
    public static final Long INGREDIENT_ID_1 = 2L;
    public static final Long INGREDIENT_ID_2 = 3L;
    public static final Long CATEGORY_ID_1 = 4L;
    public static final Long CATEGORY_ID_2 = 5L;

    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() {
        recipeCommandToRecipe = new RecipeCommandToRecipe(
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new CategoryCommandToCategory(),
                new NotesCommandToNotes());
    }

    @Test
    public void testNullObject() {
        assertNull(recipeCommandToRecipe.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(recipeCommandToRecipe.convert(new RecipeCommand()));
    }

    @Test
    public void convert() {
        // Given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setDifficulty(DIFFICULTY);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);

        recipeCommand.setNotes(notesCommand);

        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CATEGORY_ID_1);
        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(CATEGORY_ID_2);

        recipeCommand.getCategories().add(categoryCommand1);
        recipeCommand.getCategories().add(categoryCommand2);

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(INGREDIENT_ID_1);
        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand2.setId(INGREDIENT_ID_2);

        recipeCommand.getIngredients().add(ingredientCommand1);
        recipeCommand.getIngredients().add(ingredientCommand2);

        // When
        Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);

        // Then
        assertNotNull(recipe);
        assertEquals(ID, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(2, recipe.getCategories().size());
    }
}
