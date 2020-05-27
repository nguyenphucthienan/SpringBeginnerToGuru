package com.nguyenphucthienan.springmvcrest.repository;

import com.nguyenphucthienan.springmvcrest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
