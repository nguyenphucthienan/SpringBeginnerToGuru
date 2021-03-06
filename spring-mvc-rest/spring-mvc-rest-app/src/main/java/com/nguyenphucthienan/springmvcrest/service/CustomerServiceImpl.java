package com.nguyenphucthienan.springmvcrest.service;

import com.nguyenphucthienan.springmvcrest.api.v1.mapper.CustomerMapper;
import com.nguyenphucthienan.springmvcrest.model.CustomerDTO;
import com.nguyenphucthienan.springmvcrest.controller.v1.CustomerController;
import com.nguyenphucthienan.springmvcrest.domain.Customer;
import com.nguyenphucthienan.springmvcrest.exception.ResourceNotFoundException;
import com.nguyenphucthienan.springmvcrest.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer ID " + id + " not found"));

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        customerDTO.setCustomerUrl(getCustomerUrl(Objects.requireNonNull(customer).getId()));
        return customerDTO;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        return saveAndReturnCustomerDTO(customer);
    }

    @Override
    public CustomerDTO saveCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);
        return saveAndReturnCustomerDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
            if (!StringUtils.isEmpty(customerDTO.getFirstName())) {
                customer.setFirstName(customerDTO.getFirstName());
            }

            if (!StringUtils.isEmpty(customerDTO.getLastName())) {
                customer.setLastName(customerDTO.getLastName());
            }

            CustomerDTO returnCustomerDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
            returnCustomerDTO.setCustomerUrl(getCustomerUrl(id));
            return returnCustomerDTO;
        }).orElseThrow(() -> new ResourceNotFoundException("Customer ID " + id + " not found"));
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO saveAndReturnCustomerDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnCustomerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        returnCustomerDTO.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));
        return returnCustomerDTO;
    }

    private String getCustomerUrl(Long id) {
        return CustomerController.BASE_URL + "/" + id;
    }
}
