package com.nguyenphucthienan.recipeapp.service;

import com.nguyenphucthienan.recipeapp.domain.Recipe;
import com.nguyenphucthienan.recipeapp.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ImageServiceImplTest {
    @Mock
    RecipeRepository recipeRepository;

    ImageService imageService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        imageService = new ImageServiceImpl(recipeRepository);
    }

    @Test
    public void saveImageFile() throws IOException {
        // Given
        MultipartFile multipartFile = new MockMultipartFile("imagefile", "test.txt",
                "text/plain", "Nguyen Phuc Thien An".getBytes());

        Long recipeId = 1L;
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        ArgumentCaptor<Recipe> recipeArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        // When
        imageService.saveImageFile(recipeId, multipartFile);

        // Then
        verify(recipeRepository, times(1)).save(recipeArgumentCaptor.capture());
        Recipe savedRecipe = recipeArgumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
    }
}
