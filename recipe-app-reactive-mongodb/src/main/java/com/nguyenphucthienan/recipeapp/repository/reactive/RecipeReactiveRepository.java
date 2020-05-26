package com.nguyenphucthienan.recipeapp.repository.reactive;

import com.nguyenphucthienan.recipeapp.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {
}
