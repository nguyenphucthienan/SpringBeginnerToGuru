package com.nguyenphucthienan.springmvcrest.api.v1.mapper;

import com.nguyenphucthienan.springmvcrest.domain.Customer;
import com.nguyenphucthienan.springmvcrest.model.CustomerDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerMapperTest {

    private static final String FIRST_NAME = "Jimmy";
    private static final String LAST_NAME = "Fallon";

    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());
    }
}
