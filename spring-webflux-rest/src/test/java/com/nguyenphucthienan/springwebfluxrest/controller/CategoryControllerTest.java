package com.nguyenphucthienan.springwebfluxrest.controller;

import com.nguyenphucthienan.springwebfluxrest.domain.Category;
import com.nguyenphucthienan.springwebfluxrest.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class CategoryControllerTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryController categoryController;

    private WebTestClient webTestClient;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    public void getCategories() {
        given(categoryRepository.findAll())
                .willReturn(Flux.just(Category.builder().description("Category 1").build(),
                        Category.builder().description("Category 2").build()));

        webTestClient.get()
                .uri(CategoryController.BASE_URL)
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);
    }

    @Test
    public void getCategory() {
        given(categoryRepository.findById("id"))
                .willReturn(Mono.just(Category.builder().description("Category").build()));

        webTestClient.get()
                .uri(CategoryController.BASE_URL + "/id")
                .exchange()
                .expectBody(Category.class);
    }

    @Test
    public void createCategory() {
        given(categoryRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(Category.builder().description("Category").build()));

        Mono<Category> categoryMono = Mono.just(Category.builder().description("Category").build());

        webTestClient.post()
                .uri(CategoryController.BASE_URL)
                .body(categoryMono, Category.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    public void updateCategory() {
        given(categoryRepository.save(any(Category.class)))
                .willReturn(Mono.just(Category.builder().build()));

        Mono<Category> categoryMono = Mono.just(Category.builder().description("Category").build());

        webTestClient.put()
                .uri(CategoryController.BASE_URL + "/id")
                .body(categoryMono, Category.class)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void deleteCategory() {
        given(categoryRepository.deleteById("id")).willReturn(Mono.empty());

        webTestClient.delete()
                .uri(CategoryController.BASE_URL + "/id")
                .exchange()
                .expectStatus()
                .isOk();
    }
}
