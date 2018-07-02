package com.nguyenphucthienan.springwebapp.repository;

import com.nguyenphucthienan.springwebapp.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
