package com.nguyenphucthienan.recipeapp.repository;

import com.nguyenphucthienan.recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, String> {
}
