package com.nguyenphucthienan.recipeapp.bootstrap;

import com.nguyenphucthienan.recipeapp.domain.Category;
import com.nguyenphucthienan.recipeapp.domain.UnitOfMeasure;
import com.nguyenphucthienan.recipeapp.repository.CategoryRepository;
import com.nguyenphucthienan.recipeapp.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile({"dev", "prod"})
public class MySQLBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public MySQLBootstrap(CategoryRepository categoryRepository,
                          UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (categoryRepository.count() == 0L) {
            log.debug("Loading Categories");
            loadCategories();
        }

        if (unitOfMeasureRepository.count() == 0L) {
            log.debug("Loading Unit of Mesures");
            loadUnitOfMeasures();
        }
    }

    private void loadCategories() {
        Category americanCategory = new Category();
        americanCategory.setDescription("American");
        categoryRepository.save(americanCategory);

        Category italianCategory = new Category();
        italianCategory.setDescription("Italian");
        categoryRepository.save(italianCategory);

        Category mexicanCategory = new Category();
        mexicanCategory.setDescription("Mexican");
        categoryRepository.save(mexicanCategory);

        Category fastFoodCategory = new Category();
        fastFoodCategory.setDescription("Fast Food");
        categoryRepository.save(fastFoodCategory);
    }

    private void loadUnitOfMeasures() {
        UnitOfMeasure teaSpoonUom = new UnitOfMeasure();
        teaSpoonUom.setDescription("Teaspoon");
        unitOfMeasureRepository.save(teaSpoonUom);

        UnitOfMeasure tableSpoonUom = new UnitOfMeasure();
        tableSpoonUom.setDescription("Tablespoon");
        unitOfMeasureRepository.save(tableSpoonUom);

        UnitOfMeasure cupUom = new UnitOfMeasure();
        cupUom.setDescription("Cup");
        unitOfMeasureRepository.save(cupUom);

        UnitOfMeasure pinchUom = new UnitOfMeasure();
        pinchUom.setDescription("Pinch");
        unitOfMeasureRepository.save(pinchUom);

        UnitOfMeasure ounceUom = new UnitOfMeasure();
        ounceUom.setDescription("Ounce");
        unitOfMeasureRepository.save(ounceUom);

        UnitOfMeasure eachUom = new UnitOfMeasure();
        eachUom.setDescription("Each");
        unitOfMeasureRepository.save(eachUom);

        UnitOfMeasure pintUom = new UnitOfMeasure();
        pintUom.setDescription("Pint");
        unitOfMeasureRepository.save(pintUom);

        UnitOfMeasure dashUom = new UnitOfMeasure();
        dashUom.setDescription("Dash");
        unitOfMeasureRepository.save(dashUom);
    }
}
