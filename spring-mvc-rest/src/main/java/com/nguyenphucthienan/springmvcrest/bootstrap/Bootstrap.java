package com.nguyenphucthienan.springmvcrest.bootstrap;

import com.nguyenphucthienan.springmvcrest.domain.Category;
import com.nguyenphucthienan.springmvcrest.domain.Customer;
import com.nguyenphucthienan.springmvcrest.repository.CategoryRepository;
import com.nguyenphucthienan.springmvcrest.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
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
}
