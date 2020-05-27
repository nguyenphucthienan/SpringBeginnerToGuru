package com.nguyenphucthienan.springwebfluxrest.repository;

import com.nguyenphucthienan.springwebfluxrest.domain.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VendorRepository extends ReactiveMongoRepository<Vendor, String> {
}
