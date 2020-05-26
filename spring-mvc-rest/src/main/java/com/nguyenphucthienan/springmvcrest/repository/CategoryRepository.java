package com.nguyenphucthienan.springmvcrest.repository;

import com.nguyenphucthienan.springmvcrest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
