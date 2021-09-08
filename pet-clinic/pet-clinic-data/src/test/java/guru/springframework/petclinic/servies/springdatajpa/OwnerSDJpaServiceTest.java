package guru.springframework.petclinic.servies.springdatajpa;

import guru.springframework.petclinic.model.Owner;
import guru.springframework.petclinic.repositories.OwnerRepository;
import guru.springframework.petclinic.repositories.PetRepository;
import guru.springframework.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;


    final Long owner1Id = 1L;
    final Long owner2Id = 2L;
    final static String LAST_NAME_1 = "Weston";
    Owner owner1;
    Owner owner2;


    @InjectMocks
    OwnerSDJpaService service;

    @BeforeEach
    void setUp() {

        owner1 = new Owner();owner1.setFirstName("Michael");owner1.setId(owner1Id);
        owner1.setLastName(LAST_NAME_1);owner1.setAddress("123 Coconut Groove");
        owner1.setCity("Miami");owner1.setTelephone("123123123123");

        owner2 = new Owner();owner2.setFirstName("Fiona");owner2.setId(owner2Id);
        owner2.setLastName("Glenanne");owner2.setAddress("123 Coconut Groove");
        owner2.setCity("Miami");owner2.setTelephone("123123123123");
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(owner1);
        Owner owner = service.findByLastName(LAST_NAME_1);
        assertEquals(owner1Id, owner.getId());
        assertEquals(LAST_NAME_1, owner.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(owner1);owners.add(owner2);
        when(ownerRepository.findAll()).thenReturn(owners);
        Set<Owner> retOwners = service.findAll();
        assertNotNull(retOwners);
        assertEquals(2, retOwners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner1));
        Owner owner = service.findById(owner1Id);
        assertNotNull(owner);

    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner owner = service.findById(owner1Id);
        assertNull(owner);

    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(owner1);
        Owner owner = service.save(owner1);
        assertNotNull(owner);
    }

    @Test
    void delete() {
        service.delete(owner1);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(owner1Id);
        verify(ownerRepository).deleteById(anyLong());
    }
}