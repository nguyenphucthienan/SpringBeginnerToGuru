package com.nguyenphucthienan.recipeapp.converter;

import com.nguyenphucthienan.recipeapp.command.CategoryCommand;
import com.nguyenphucthienan.recipeapp.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    public static final String ID = "1";
    public static final String DESCRIPTION = "description";

    CategoryToCategoryCommand categoryToCategoryCommand;

    @Before
    public void setUp() {
        categoryToCategoryCommand = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(categoryToCategoryCommand.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(categoryToCategoryCommand.convert(new Category()));
    }

    @Test
    public void convert() {
        // Given
        Category category = new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION);

        // When
        CategoryCommand categoryCommand = categoryToCategoryCommand.convert(category);

        // Then
        assertEquals(ID, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}
