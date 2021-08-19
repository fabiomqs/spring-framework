package guru.springframework.petclinic.controllers;

import guru.springframework.petclinic.model.Owner;
import guru.springframework.petclinic.servies.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    MockMvc mockMvc;

    Set<Owner> owners;
    final Long owner1Id = 1L;
    final Long owner2Id = 2L;
    Owner owner1;
    Owner owner2;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owner1 = new Owner();owner1.setFirstName("Michael");owner1.setId(owner1Id);
        owner1.setLastName("Weston");owner1.setAddress("123 Coconut Groove");
        owner1.setCity("Miami");owner1.setTelephone("123123123123");
        owner2 = new Owner();owner2.setFirstName("Fiona");owner2.setId(owner2Id);
        owner2.setLastName("Glenanne");owner2.setAddress("123 Coconut Groove");
        owner2.setCity("Miami");owner2.setTelephone("123123123123");
        owners.add(owner1);owners.add(owner2);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }


    @Test
    void listOwners() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void listOwners2() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);
        mockMvc.perform(get("/owners/"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void listOwnersIndex() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);
        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void listOwnersIndex2() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);
        mockMvc.perform(get("/owners/index.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notimplemented"));
        //TODO
        //Replace deprecated method
        verifyZeroInteractions(ownerService);
    }
}