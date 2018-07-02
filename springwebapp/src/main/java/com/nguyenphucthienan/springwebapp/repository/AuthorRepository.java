package com.nguyenphucthienan.springwebapp.repository;

import com.nguyenphucthienan.springwebapp.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
