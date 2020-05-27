package com.nguyenphucthienan.springwebfluxrest.repository;

import com.nguyenphucthienan.springwebfluxrest.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
}
