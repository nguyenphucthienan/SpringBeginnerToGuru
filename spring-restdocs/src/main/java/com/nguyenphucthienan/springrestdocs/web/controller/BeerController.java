package com.nguyenphucthienan.springrestdocs.web.controller;

import com.nguyenphucthienan.springrestdocs.repository.BeerRepository;
import com.nguyenphucthienan.springrestdocs.web.mapper.BeerMapper;
import com.nguyenphucthienan.springrestdocs.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping(BeerController.BASE_URL)
@RestController
public class BeerController {

    public static final String BASE_URL = "/api/v1/beers";

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @GetMapping("/{id}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable UUID id) {
        return new ResponseEntity<>(beerMapper.beerToBeerDto(beerRepository.findById(id).get()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveBeer(@RequestBody @Validated BeerDto beerDto) {
        beerRepository.save(beerMapper.beerDtoToBeer(beerDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBeer(@PathVariable UUID id, @RequestBody @Validated BeerDto beerDto) {
        beerRepository.findById(id).ifPresent(beer -> {
            beer.setBeerName(beerDto.getBeerName());
            beer.setBeerStyle(beerDto.getBeerStyle().name());
            beer.setPrice(beerDto.getPrice());
            beer.setUpc(beerDto.getUpc());
            beerRepository.save(beer);
        });

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
