package guru.springframework.web.controller;


import guru.springframework.repositories.BeerRepository;
import guru.springframework.web.mappers.BeerMapper;
import guru.springframework.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping(BeerController.BASE_URL)
@RestController
public class BeerController {

    public static final String BASE_URL = "/api/v1/beer";

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @GetMapping
    public ResponseEntity<List<BeerDto>> getAllBeers() {
        return new ResponseEntity<List<BeerDto>>(
                beerRepository.findAll().stream()
                .map(beer -> beerMapper.BeerToBeerDto(beer))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId){

        return new ResponseEntity<>(beerMapper.BeerToBeerDto(beerRepository.findById(beerId).get()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDto beerDto){

        beerRepository.save(beerMapper.BeerDtoToBeer(beerDto));

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDto beerDto){
        beerRepository.findById(beerId).ifPresent(beer -> {
            beer.setBeerName(beerDto.getBeerName());
            beer.setBeerStyle(beerDto.getBeerStyle().name());
            beer.setPrice(beerDto.getPrice());
            beer.setUpc(beerDto.getUpc());

            beerRepository.save(beer);
        });

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
