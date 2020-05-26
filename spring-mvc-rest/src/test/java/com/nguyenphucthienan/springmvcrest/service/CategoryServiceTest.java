package com.nguyenphucthienan.springmvcrest.service;

import com.nguyenphucthienan.springmvcrest.api.v1.mapper.CategoryMapper;
import com.nguyenphucthienan.springmvcrest.api.v1.model.CategoryDTO;
import com.nguyenphucthienan.springmvcrest.domain.Category;
import com.nguyenphucthienan.springmvcrest.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    private static final Long ID = 1L;
    public static final String NAME = "Fresh";

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    public void getAllCategories() {
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTOs = categoryService.getAllCategories();

        assertEquals(3, categoryDTOs.size());
    }

    @Test
    public void getCategoryByName() {
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(Optional.of(category));

        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}
