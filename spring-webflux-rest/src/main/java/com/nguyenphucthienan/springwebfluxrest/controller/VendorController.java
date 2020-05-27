package com.nguyenphucthienan.springwebfluxrest.controller;

import com.nguyenphucthienan.springwebfluxrest.domain.Vendor;
import com.nguyenphucthienan.springwebfluxrest.repository.VendorRepository;
import org.apache.commons.lang3.StringUtils;
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
    Flux<Vendor> getVendors() {
        return vendorRepository.findAll();
    }

    @GetMapping("/{id}")
    Mono<Vendor> getVendor(@PathVariable String id) {
        return vendorRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Mono<Void> createVendor(@RequestBody Publisher<Vendor> vendorStream) {
        return vendorRepository.saveAll(vendorStream).then();
    }

    @PutMapping("/{id}")
    Mono<Vendor> updateVendor(@PathVariable String id, @RequestBody Vendor vendor) {
        vendor.setId(id);
        return vendorRepository.save(vendor);
    }

    @PatchMapping("/{id}")
    Mono<Vendor> patchVendor(@PathVariable String id, @RequestBody Vendor vendor) {
        return vendorRepository.findById(id)
                .flatMap(foundVendor -> {
                    if (StringUtils.isNotEmpty(vendor.getFirstName())) {
                        foundVendor.setFirstName(vendor.getFirstName());
                    }
                    if (StringUtils.isNotEmpty(vendor.getLastName())) {
                        foundVendor.setLastName(vendor.getLastName());
                    }
                    return vendorRepository.save(foundVendor);
                });
    }

    @DeleteMapping("/{id}")
    Mono<Void> deleteCategory(@PathVariable String id) {
        return vendorRepository.deleteById(id).then(Mono.empty());
    }
}
