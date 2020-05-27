package com.nguyenphucthienan.springmvcrest.controller.v1;

import com.nguyenphucthienan.springmvcrest.api.v1.model.CustomerDTO;
import com.nguyenphucthienan.springmvcrest.controller.v1.handler.RestResponseEntityExceptionHandler;
import com.nguyenphucthienan.springmvcrest.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest extends AbstractRestControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getCustomers() throws Exception {
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName("Michael");
        customerDTO1.setLastName("Weston");
        customerDTO1.setCustomerUrl(CustomerController.BASE_URL + "/1");

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setFirstName("Sam");
        customerDTO2.setLastName("Axe");
        customerDTO2.setCustomerUrl(CustomerController.BASE_URL + "/2");

        when(customerService.getCustomers()).thenReturn(Arrays.asList(customerDTO1, customerDTO2));

        mockMvc.perform(get(CustomerController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void getCustomerById() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Michael");
        customerDTO.setLastName("Weston");
        customerDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        mockMvc.perform(get(CustomerController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Michael")));
    }

    @Test
    public void createCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Fred");
        customerDTO.setLastName("Flintstone");

        CustomerDTO returnCustomerDTO = new CustomerDTO();
        returnCustomerDTO.setFirstName(customerDTO.getFirstName());
        returnCustomerDTO.setLastName(customerDTO.getLastName());
        returnCustomerDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.saveCustomer(customerDTO)).thenReturn(returnCustomerDTO);

        mockMvc.perform(post(CustomerController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Fred")))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Fred");
        customerDTO.setLastName("Flintstone");

        CustomerDTO returnCustomerDTO = new CustomerDTO();
        returnCustomerDTO.setFirstName(customerDTO.getFirstName());
        returnCustomerDTO.setLastName(customerDTO.getLastName());
        returnCustomerDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.saveCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnCustomerDTO);

        mockMvc.perform(put(CustomerController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Fred")))
                .andExpect(jsonPath("$.lastName", equalTo("Flintstone")))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    public void testPatchCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Fred");

        CustomerDTO returnCustomerDTO = new CustomerDTO();
        returnCustomerDTO.setFirstName(customerDTO.getFirstName());
        returnCustomerDTO.setLastName("Flintstone");
        returnCustomerDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnCustomerDTO);

        mockMvc.perform(patch(CustomerController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Fred")))
                .andExpect(jsonPath("$.lastName", equalTo("Flintstone")))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete(CustomerController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(anyLong());
    }
}
