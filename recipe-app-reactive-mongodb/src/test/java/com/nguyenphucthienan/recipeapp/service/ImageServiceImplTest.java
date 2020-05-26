package com.nguyenphucthienan.recipeapp.service;

import com.nguyenphucthienan.recipeapp.domain.Recipe;
import com.nguyenphucthienan.recipeapp.repository.reactive.RecipeReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ImageServiceImplTest {

    @Mock
    private RecipeReactiveRepository recipeReactiveRepository;

    private ImageService imageService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        imageService = new ImageServiceImpl(recipeReactiveRepository);
    }

    @Test
    public void saveImageFile() throws IOException {
        // Given
        MultipartFile multipartFile = new MockMultipartFile("imagefile", "test.txt",
                "text/plain", "Nguyen Phuc Thien An".getBytes());

        String recipeId = "1";
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));
        when(recipeReactiveRepository.save(any(Recipe.class))).thenReturn(Mono.just(recipe));

        ArgumentCaptor<Recipe> recipeArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        // When
        imageService.saveImageFile(recipeId, multipartFile);

        // Then
        verify(recipeReactiveRepository, times(1)).save(recipeArgumentCaptor.capture());
        Recipe savedRecipe = recipeArgumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
    }
}
