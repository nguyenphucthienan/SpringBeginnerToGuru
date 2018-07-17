package com.nguyenphucthienan.recipeapp.converter;

import com.nguyenphucthienan.recipeapp.command.CategoryCommand;
import com.nguyenphucthienan.recipeapp.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {
    public static final Long ID = 1L;
    public static final String DESCRIPTION = "description";

    CategoryCommandToCategory categoryCommandToCategory;

    @Before
    public void setUp() {
        categoryCommandToCategory = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() {
        assertNull(categoryCommandToCategory.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(categoryCommandToCategory.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {
        // Given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID);
        categoryCommand.setDescription(DESCRIPTION);

        // When
        Category category = categoryCommandToCategory.convert(categoryCommand);

        // Then
        assertEquals(ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}
