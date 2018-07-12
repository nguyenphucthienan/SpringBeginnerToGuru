package com.nguyenphucthienan.recipeapp.repository;

import com.nguyenphucthienan.recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
