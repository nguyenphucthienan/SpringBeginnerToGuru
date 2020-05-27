package com.nguyenphucthienan.springmvcrest.controller.v1;

import com.nguyenphucthienan.springmvcrest.api.v1.model.CategoryDTO;
import com.nguyenphucthienan.springmvcrest.api.v1.model.CategoryListDTO;
import com.nguyenphucthienan.springmvcrest.config.SwaggerConfig;
import com.nguyenphucthienan.springmvcrest.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = {SwaggerConfig.CATEGORY_TAG})
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "Get categories")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getCategories() {
        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @ApiOperation(value = "Get a category")
    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategory(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }
}
