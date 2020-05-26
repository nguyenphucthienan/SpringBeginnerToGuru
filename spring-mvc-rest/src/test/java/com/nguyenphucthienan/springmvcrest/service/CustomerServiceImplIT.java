package com.nguyenphucthienan.springmvcrest.service;

import com.nguyenphucthienan.springmvcrest.api.v1.mapper.CustomerMapper;
import com.nguyenphucthienan.springmvcrest.api.v1.model.CustomerDTO;
import com.nguyenphucthienan.springmvcrest.bootstrap.Bootstrap;
import com.nguyenphucthienan.springmvcrest.domain.Customer;
import com.nguyenphucthienan.springmvcrest.repository.CategoryRepository;
import com.nguyenphucthienan.springmvcrest.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private CustomerService customerService;

    @BeforeEach
    public void setUp() throws Exception {
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
        bootstrap.run(); // Load data
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void patchCustomerUpdateFirstName() {
        String updatedName = "Updated Name";
        Long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);

        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(updatedName);

        customerService.patchCustomer(id, customerDTO);
        customerRepository.findById(id).ifPresent((updatedCustomer) -> {
            assertNotNull(updatedCustomer);
            assertEquals(updatedName, updatedCustomer.getFirstName());
            assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstName())));
            assertThat(originalLastName, equalTo(updatedCustomer.getLastName()));
        });
    }

    @Test
    public void patchCustomerUpdateLastName() {
        String updatedName = "Updated Name";
        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);

        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(updatedName);

        customerService.patchCustomer(id, customerDTO);
        customerRepository.findById(id).ifPresent((updatedCustomer) -> {
            assertNotNull(updatedCustomer);
            assertEquals(updatedName, updatedCustomer.getLastName());
            assertThat(originalFirstName, equalTo(updatedCustomer.getFirstName()));
            assertThat(originalLastName, not(equalTo(updatedCustomer.getLastName())));
        });
    }

    private Long getCustomerIdValue() {
        List<Customer> customers = customerRepository.findAll();
        return customers.get(0).getId();
    }
}
