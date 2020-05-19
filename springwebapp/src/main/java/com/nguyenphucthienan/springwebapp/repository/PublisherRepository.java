package com.nguyenphucthienan.springwebapp.repository;

import com.nguyenphucthienan.springwebapp.domain.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
