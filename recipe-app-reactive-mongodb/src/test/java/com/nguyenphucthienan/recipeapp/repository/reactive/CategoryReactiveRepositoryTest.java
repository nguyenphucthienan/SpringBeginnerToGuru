package com.nguyenphucthienan.recipeapp.repository.reactive;

import com.nguyenphucthienan.recipeapp.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    @Autowired
    private CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() {
        Category category = new Category();
        category.setDescription("Description");

        categoryReactiveRepository.save(category).block();

        Long count = categoryReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    public void testFindByDescription() {
        Category category = new Category();
        category.setDescription("Description");

        categoryReactiveRepository.save(category).block();

        Category fetchedCategory = categoryReactiveRepository.findByDescription("Description").block();
        assertNotNull(fetchedCategory);
    }
}
