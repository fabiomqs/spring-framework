package guru.springframework.services;

import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.api.v1.mapper.VendorMapper;
import guru.springframework.controllers.v1.VendorController;
import guru.springframework.domain.Vendor;
import guru.springframework.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class VendorServiceTest {

    private final String NAME = "Western Tasty Fruits Ltd.";

    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void getAllVendors() throws Exception {
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());
        when(vendorRepository.findAll()).thenReturn(vendors);
        List<VendorDTO> vendorsDTO = vendorService.getAllVendors();
        assertEquals(3, vendorsDTO.size());
    }

    @Test
    public void getVendorById() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setId(1L);vendor.setName(NAME);

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        VendorDTO vendorDTO = vendorService.getVendorById(1L);
        then(vendorRepository).should(times(1)).findById(anyLong());
        assertThat(vendorDTO.getName(), is(equalTo(NAME)));

    }

    @Test
    public void createNewVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO savedDTO = vendorService.createNewVendor(vendorDTO);

        assertEquals(vendorDTO.getName(), savedDTO.getName());
        assertEquals(VendorController.BASE_URL + "/1", savedDTO.getVendorUrl());

    }

    @Test
    public void saveVendorByDTO() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO savedDTO = vendorService.saveVendorByDTO(1L, vendorDTO);

        assertEquals(vendorDTO.getName(), savedDTO.getName());
        assertEquals(VendorController.BASE_URL + "/1", savedDTO.getVendorUrl());
    }

    @Test
    public void deleteCustomerById() throws Exception {
        vendorService.deleteVendorById(1L);
        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}
