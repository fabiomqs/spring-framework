package guru.springframework.controllers.v1;

import guru.springframework.domain.Vendor;
import guru.springframework.repository.VendorRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorRepository vendorRepository;

    public VendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @GetMapping
    Flux<Vendor> list(){
        return vendorRepository.findAll();
    }

    @GetMapping("/{id}")
    Mono<Vendor> getById(@PathVariable String id){
        return vendorRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Mono<Void> create(@RequestBody Publisher<Vendor> vendorStream) {
        return vendorRepository.saveAll(vendorStream).then();
    }

    @PutMapping("/{id}")
    Mono<Vendor> update(@PathVariable String id, @RequestBody Vendor vendor){
        vendor.setId(id);
        return vendorRepository.save(vendor);
    }

    @PatchMapping("/{id}")
    Mono<Vendor> patch(@PathVariable String id, @RequestBody Vendor vendor){


        return (Mono<Vendor>) vendorRepository.findById(id)
                .flatMap(vendor1 -> {
                    boolean patch = false;
                    if(vendor.getFirstName() != null && !vendor1.getFirstName().equals(vendor.getFirstName())){
                        vendor1.setFirstName(vendor.getFirstName());
                        patch = true;
                    }
                    if(vendor.getLastName() != null && !vendor1.getLastName().equals(vendor.getLastName())){
                        vendor1.setLastName(vendor.getLastName());
                        patch = true;
                    }
                    if (patch) {
                        return vendorRepository.save(vendor1);
                    }
                    return Mono.just(vendor1);
                });

    }
}
