package com.nguyenphucthienan.springwebapp.repository;

import com.nguyenphucthienan.springwebapp.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
