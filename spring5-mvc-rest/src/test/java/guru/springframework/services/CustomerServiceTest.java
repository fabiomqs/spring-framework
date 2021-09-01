package guru.springframework.services;

import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.api.v1.model.mapper.CustomerMapper;
import guru.springframework.controllers.v1.CustomerController;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    public static final String FIRST_NAME = "Joe";
    public static final String LAST_NAME = "Gunn";
    public static final String CUSTOMER_URL = "api/v1/customers/Joe";
    public static final long ID = 1L;

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void getAllCustomers() throws Exception {
        //given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        //when
        when(customerRepository.findAll()).thenReturn(customers);

        //then
        List<CustomerDTO> customersDTO = customerService.getAllCustomers();

        assertEquals(3, customersDTO.size());

    }

    @Test
    public void getCategoryById() throws Exception {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);


        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(customer));

        CustomerDTO customerDTO = customerService.getCustomerById(ID);


        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());


    }

    @Test
    public void createNewCustomer() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(1l);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
        assertEquals(CustomerController.BASE_URL + "/1", savedDto.getCustomerUrl());
    }

    @Test
    public void saveCustomerByDTO() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(1l);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.saveCustomerByDTO(1L, customerDTO);

        //then
        assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
        assertEquals(CustomerController.BASE_URL + "/1", savedDto.getCustomerUrl());
    }

    @Test
    public void deleteCustomerById() throws Exception {

        Long id = 1L;

        customerService.deleteCustomerById(id);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}
