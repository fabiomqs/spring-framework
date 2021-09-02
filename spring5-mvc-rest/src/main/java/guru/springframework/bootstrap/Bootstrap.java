package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Vendor;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {
    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();fruits.setName("Fruits");
        Category dried = new Category();dried.setName("Dried");
        Category fresh = new Category();fresh.setName("Fresh");
        Category exotic = new Category();exotic.setName("Exotic");
        Category nuts = new Category();nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        log.info("Data Loaded (Categories)= " + categoryRepository.count());

        Customer joe = new Customer();joe.setFirstName("Joe");joe.setLastName("Newman");
        Customer michael = new Customer();michael.setFirstName("Michael");michael.setLastName("Lachappele");
        Customer david = new Customer();david.setFirstName("David");david.setLastName("Winter");
        Customer anne = new Customer();anne.setFirstName("Anne");anne.setLastName("Hine");
        Customer alice = new Customer();alice.setFirstName("Alice");alice.setLastName("Eastman");
        Customer utku = new Customer();utku.setFirstName("Utku");utku.setLastName("Turel");
        Customer dennis = new Customer();dennis.setFirstName("Dennis");dennis.setLastName("Whatever");

        customerRepository.save(joe);customerRepository.save(michael);
        customerRepository.save(david);customerRepository.save(anne);
        customerRepository.save(alice);customerRepository.save(dennis);

        log.info("Data Loaded (Customers)= " + customerRepository.count());

        Vendor western = new Vendor();western.setName("Western Tasty Fruits Ltd.");
        Vendor exoticC = new Vendor();exoticC.setName("Exotic Fruits Company");
        Vendor home = new Vendor();home.setName("Home Fruits");
        Vendor fun = new Vendor();fun.setName("Fun Fresh Fruits Ltd.");
        Vendor nutsC = new Vendor();nutsC.setName("Nuts for Nuts Company");
        Vendor random = new Vendor();random.setName("Random new Vendor");

        vendorRepository.save(western);vendorRepository.save(exoticC);
        vendorRepository.save(home);vendorRepository.save(fun);
        vendorRepository.save(nutsC);vendorRepository.save(random);

        log.info("Data Loaded (Vendors)= " + vendorRepository.count());

    }
}
