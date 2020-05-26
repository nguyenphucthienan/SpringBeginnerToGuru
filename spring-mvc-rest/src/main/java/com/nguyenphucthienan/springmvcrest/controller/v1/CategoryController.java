package com.nguyenphucthienan.springmvcrest.controller.v1;

import com.nguyenphucthienan.springmvcrest.api.v1.model.CategoryDTO;
import com.nguyenphucthienan.springmvcrest.api.v1.model.CategoryListDTO;
import com.nguyenphucthienan.springmvcrest.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getCategories() {
        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategory(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }
}
