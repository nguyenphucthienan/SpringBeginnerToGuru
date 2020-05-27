package com.nguyenphucthienan.springwebfluxrest.controller;

import com.nguyenphucthienan.springwebfluxrest.domain.Category;
import com.nguyenphucthienan.springwebfluxrest.repository.CategoryRepository;
import org.apache.commons.lang3.StringUtils;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    Flux<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    Mono<Category> getCategory(@PathVariable String id) {
        return categoryRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<Void> createCategory(@RequestBody Publisher<Category> categoryStream) {
        return categoryRepository.saveAll(categoryStream).then();
    }

    @PutMapping("/{id}")
    Mono<Category> updateCategory(@PathVariable String id, @RequestBody Category category) {
        category.setId(id);
        return categoryRepository.save(category);
    }

    @PatchMapping("/{id}")
    Mono<Category> patchCategory(@PathVariable String id, @RequestBody Category category) {
        return categoryRepository.findById(id)
                .flatMap(foundCategory -> {
                    if (StringUtils.isNotEmpty(category.getDescription())) {
                        foundCategory.setDescription(category.getDescription());
                    }
                    return categoryRepository.save(foundCategory);
                });
    }

    @DeleteMapping("/{id}")
    Mono<Void> deleteCategory(@PathVariable String id) {
        return categoryRepository.deleteById(id).then(Mono.empty());
    }
}
