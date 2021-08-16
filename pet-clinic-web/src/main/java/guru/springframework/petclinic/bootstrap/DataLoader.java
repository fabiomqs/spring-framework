package guru.springframework.petclinic.bootstrap;

import guru.springframework.petclinic.model.Owner;
import guru.springframework.petclinic.model.Pet;
import guru.springframework.petclinic.model.PetType;
import guru.springframework.petclinic.model.Vet;
import guru.springframework.petclinic.servies.OwnerService;
import guru.springframework.petclinic.servies.PetTypeService;
import guru.springframework.petclinic.servies.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();owner1.setFirstName("Michael");owner1.setLastName("Weston");
        owner1.setAddress("123 Coconut Groove");owner1.setCity("Miami");owner1.setTelephone("123123123123");

        Pet mikesPet = new Pet();
        mikesPet.setName("Rosco");mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setOwner(owner1);mikesPet.setPetType(savedDogPetType);
        owner1.getPets().add(mikesPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();owner2.setFirstName("Fiona");owner2.setLastName("Glenanne");
        owner2.setAddress("123 Coconut Groove");owner2.setCity("Miami");owner2.setTelephone("123123123123");

        Pet fionasPet = new Pet();
        fionasPet.setName("Just Cat");fionasPet.setBirthDate(LocalDate.now());
        fionasPet.setOwner(owner2);fionasPet.setPetType(savedCatPetType);
        owner2.getPets().add(fionasPet);

        ownerService.save(owner2);
        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();vet1.setFirstName("Sam");vet1.setLastName("Axe");vetService.save(vet1);
        Vet vet2 = new Vet();vet2.setFirstName("Jessie");vet2.setLastName("Porter");vetService.save(vet2);
        System.out.println("Loaded Vets....");
    }
}
