package com.nguyenphucthienan.recipeapp.converter;

import com.nguyenphucthienan.recipeapp.command.RecipeCommand;
import com.nguyenphucthienan.recipeapp.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    public static final String ID = "1";
    public static final String DESCRIPTION = "description";
    public static final Integer PREP_TIME = 6;
    public static final Integer COOK_TIME = 8;
    public static final Integer SERVINGS = 3;
    public static final String SOURCE = "source";
    public static final String URL = "url";
    public static final String DIRECTIONS = "directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final String NOTES_ID = "1";
    public static final String INGREDIENT_ID_1 = "2";
    public static final String INGREDIENT_ID_2 = "3";
    public static final String CATEGORY_ID_1 = "4";
    public static final String CATEGORY_ID_2 = "5";

    RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() {
        recipeToRecipeCommand = new RecipeToRecipeCommand(
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new CategoryToCategoryCommand(),
                new NotesToNotesCommand());
    }

    @Test
    public void testNullObject() {
        assertNull(recipeToRecipeCommand.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(recipeToRecipeCommand.convert(new Recipe()));
    }

    @Test
    public void convert() {
        // Given
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        recipe.setNotes(notes);

        Category category1 = new Category();
        category1.setId(CATEGORY_ID_1);
        Category category2 = new Category();
        category2.setId(CATEGORY_ID_2);

        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGREDIENT_ID_1);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGREDIENT_ID_2);

        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        // When
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);

        // Then
        assertNotNull(recipeCommand);
        assertEquals(ID, recipeCommand.getId());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(2, recipeCommand.getIngredients().size());
        assertEquals(2, recipeCommand.getCategories().size());
    }
}
