package guru.springframework.petclinic.servies.map;

import guru.springframework.petclinic.model.Owner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long owner1Id = 1L;
    final Long owner2Id = 2L;
    Owner owner1;
    Owner owner2;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        owner1 = new Owner();owner1.setFirstName("Michael");owner1.setId(owner1Id);
        owner1.setLastName("Weston");owner1.setAddress("123 Coconut Groove");
        owner1.setCity("Miami");owner1.setTelephone("123123123123");
        ownerMapService.save(owner1);

        owner2 = new Owner();owner2.setFirstName("Fiona");owner2.setId(owner2Id);
        owner2.setLastName("Glenanne");owner2.setAddress("123 Coconut Groove");
        owner2.setCity("Miami");owner2.setTelephone("123123123123");
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(owner1Id);
        assertEquals(owner1Id, owner.getId());
    }

    @Test
    void saveExistingId() {
       Owner savedOwner = ownerMapService.save(owner2);
       assertEquals(owner2Id, savedOwner.getId());
        assertEquals(2, ownerMapService.findAll().size());
    }

    @Test
    void saveNoId() {
        Owner owner3 = new Owner();owner3.setFirstName("Fiona");
        Owner savedOwner = ownerMapService.save(owner3);
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
        assertEquals(2, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(owner1Id);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerMapService.delete(owner1);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner weston = ownerMapService.findByLastName("Weston");
        assertNotNull(weston);
        assertEquals(owner1Id, weston.getId());
        weston = ownerMapService.findByLastName("weston");
        assertNotNull(weston);

    }
    @Test
    void findByLastNameNotFound() {
        Owner weston = ownerMapService.findByLastName("Westons");
        assertNull(weston);
    }
}