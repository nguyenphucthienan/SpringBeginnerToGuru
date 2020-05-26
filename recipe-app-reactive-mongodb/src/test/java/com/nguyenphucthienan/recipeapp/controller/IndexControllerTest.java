package com.nguyenphucthienan.recipeapp.controller;

import com.nguyenphucthienan.recipeapp.domain.Recipe;
import com.nguyenphucthienan.recipeapp.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import reactor.core.publisher.Flux;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    @Mock
    private Model model;

    @Mock
    private RecipeService recipeService;

    private IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void testMockMVC() throws Exception {
        when(recipeService.getRecipes()).thenReturn(Flux.empty());

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void getIndexPage() {
        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe = new Recipe();
        recipe.setId("1");
        recipes.add(recipe);
        recipes.add(new Recipe());

        when(recipeService.getRecipes()).thenReturn(Flux.fromIterable(recipes));

        String viewName = indexController.getIndexPage(model);
        ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        assertEquals("index", viewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

        List<Recipe> recipeList = argumentCaptor.getValue();
        assertEquals(2, recipeList.size());
    }
}
