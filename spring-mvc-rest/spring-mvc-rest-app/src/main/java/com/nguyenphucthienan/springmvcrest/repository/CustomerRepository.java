package com.nguyenphucthienan.springmvcrest.repository;

import com.nguyenphucthienan.springmvcrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
