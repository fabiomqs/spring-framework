package guru.springframework.api.v1.mapper;

import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.api.v1.model.mapper.CustomerMapper;
import guru.springframework.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerMapperTest {

    public static final String FIRST_NAME = "Joe";
    public static final String LAST_NAME = "Gunn";
    public static final long ID = 1L;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() throws Exception {

        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());


    }
}