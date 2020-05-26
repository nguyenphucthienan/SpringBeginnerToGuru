package com.nguyenphucthienan.recipeapp.controller;

import com.nguyenphucthienan.recipeapp.command.RecipeCommand;
import com.nguyenphucthienan.recipeapp.service.ImageService;
import com.nguyenphucthienan.recipeapp.service.RecipeService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Ignore
public class ImageControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    ImageService imageService;

    ImageController imageController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        imageController = new ImageController(recipeService, imageService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void showUploadForm() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId("1");

        when(recipeService.findCommandById(anyString())).thenReturn(Mono.just(recipeCommand));

        mockMvc.perform(get("/recipe/1/image-upload"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/image-upload-form"));

        verify(recipeService, times(1)).findCommandById(anyString());
    }

    @Test
    public void handlePostImage() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "test.txt",
                "text/plain", "Nguyen Phuc Thien An".getBytes());

        when(imageService.saveImageFile(anyString(), any())).thenReturn(Mono.empty());

        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/1/show"));

        verify(imageService, times(1)).saveImageFile(anyString(), any());
    }

    // @Test
    // public void renderImageFromDb() throws Exception {
    //     // Given
    //     RecipeCommand recipeCommand = new RecipeCommand();
    //     recipeCommand.setId("1");
    //
    //     String str = "Image text";
    //     Byte[] bytesBoxed = new Byte[str.getBytes().length];
    //
    //     int i = 0;
    //     for (byte primitiveByte : str.getBytes()) {
    //         bytesBoxed[i++] = primitiveByte;
    //     }
    //
    //     recipeCommand.setImage(bytesBoxed);
    //     when(recipeService.findCommandById(anyString())).thenReturn(Mono.just(recipeCommand));
    //
    //     // When
    //     MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/image"))
    //             .andExpect(status().isOk())
    //             .andReturn().getResponse();
    //
    //     // Then
    //     byte[] responseBytes = response.getContentAsByteArray();
    //     assertEquals(str.getBytes().length, responseBytes.length);
    // }
}
