package com.nguyenphucthienan.springmvcrest.api.v1.mapper;

import com.nguyenphucthienan.springmvcrest.domain.Customer;
import com.nguyenphucthienan.springmvcrest.model.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
