package com.nguyenphucthienan.recipeapp.repository.reactive;

import com.nguyenphucthienan.recipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryTest {

    @Autowired
    private RecipeReactiveRepository recipeReactiveRepository;

    public RecipeReactiveRepositoryTest() {
    }

    @Before
    public void setUp() throws Exception {
        recipeReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() {
        Recipe recipe = new Recipe();
        recipe.setDescription("Description");

        recipeReactiveRepository.save(recipe).block();

        Long count = recipeReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), count);
    }
}
