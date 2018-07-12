package com.nguyenphucthienan.recipeapp.controller;

import com.nguyenphucthienan.recipeapp.domain.Category;
import com.nguyenphucthienan.recipeapp.domain.UnitOfMeasure;
import com.nguyenphucthienan.recipeapp.repository.CategoryRepository;
import com.nguyenphucthienan.recipeapp.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        Optional<Category> optionalCategory = categoryRepository.findByDescription("Italian");
        Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRepository.findByDescription("Cup");

        System.out.println("Category ID: " + optionalCategory.get().getId());
        System.out.println("Unit of Measure ID: " + optionalUnitOfMeasure.get().getId());

        return "index";
    }
}
