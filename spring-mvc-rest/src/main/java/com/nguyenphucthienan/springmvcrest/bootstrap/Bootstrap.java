package com.nguyenphucthienan.springmvcrest.bootstrap;

import com.nguyenphucthienan.springmvcrest.domain.Category;
import com.nguyenphucthienan.springmvcrest.domain.Customer;
import com.nguyenphucthienan.springmvcrest.domain.Vendor;
import com.nguyenphucthienan.springmvcrest.repository.CategoryRepository;
import com.nguyenphucthienan.springmvcrest.repository.CustomerRepository;
import com.nguyenphucthienan.springmvcrest.repository.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository,
                     VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);
    }

    private void loadCustomers() {
        Customer michael = new Customer();
        michael.setId(1L);
        michael.setFirstName("Michael");
        michael.setLastName("Weston");

        Customer sam = new Customer();
        sam.setId(2L);
        sam.setFirstName("Sam");
        sam.setLastName("Axe");

        customerRepository.save(michael);
        customerRepository.save(sam);
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor 1");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor 2");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);
    }
}
