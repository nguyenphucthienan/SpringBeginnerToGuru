package com.nguyenphucthienan.recipeapp.converter;

import com.nguyenphucthienan.recipeapp.command.IngredientCommand;
import com.nguyenphucthienan.recipeapp.command.UnitOfMeasureCommand;
import com.nguyenphucthienan.recipeapp.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    public static final Long ID = 1L;
    public static final String DESCRIPTION = "Cheeseburger";
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final Long UNIT_OF_MEASURE_ID = 2L;

    IngredientCommandToIngredient ingredientCommandToIngredient;

    @Before
    public void setUp() {
        ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject() {
        assertNull(ingredientCommandToIngredient.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(ingredientCommandToIngredient.convert(new IngredientCommand()));
    }

    @Test
    public void convert() {
        // Given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);

        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UNIT_OF_MEASURE_ID);

        ingredientCommand.setUnitOfMeasure(unitOfMeasureCommand);

        // When
        Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);

        // Then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UNIT_OF_MEASURE_ID, ingredient.getUnitOfMeasure().getId());
    }

    @Test
    public void convertWithNullUnitOfMeasure() {
        // Given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setUnitOfMeasure(null);

        // When
        Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);

        // Then
        assertNotNull(ingredient);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
    }
}
