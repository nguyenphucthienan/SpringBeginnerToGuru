package com.nguyenphucthienan.mongodb.repository;

import com.nguyenphucthienan.mongodb.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
}
