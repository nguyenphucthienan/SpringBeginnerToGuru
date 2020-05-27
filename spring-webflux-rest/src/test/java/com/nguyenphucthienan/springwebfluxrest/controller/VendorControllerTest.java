package com.nguyenphucthienan.springwebfluxrest.controller;

import com.nguyenphucthienan.springwebfluxrest.domain.Vendor;
import com.nguyenphucthienan.springwebfluxrest.repository.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class VendorControllerTest {

    @Mock
    private VendorRepository vendorRepository;

    @InjectMocks
    private VendorController controller;

    private WebTestClient webTestClient;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        webTestClient = WebTestClient.bindToController(controller).build();
    }

    @Test
    public void getVendors() {
        given(vendorRepository.findAll())
                .willReturn(Flux.just(Vendor.builder().firstName("Fred").lastName("Flintstone").build(),
                        Vendor.builder().firstName("Barney").lastName("Rubble").build()));

        webTestClient.get()
                .uri(VendorController.BASE_URL)
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    public void getVendor() {
        given(vendorRepository.findById("id"))
                .willReturn(Mono.just(Vendor.builder().firstName("Jimmy").lastName("Johns").build()));

        webTestClient.get()
                .uri(VendorController.BASE_URL + "/id")
                .exchange()
                .expectBody(Vendor.class);
    }

    @Test
    public void createVendor() {
        given(vendorRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(Vendor.builder().build()));

        Mono<Vendor> vendorMono = Mono.just(Vendor.builder().firstName("Jimmy").lastName("Johns").build());

        webTestClient.post()
                .uri(VendorController.BASE_URL)
                .body(vendorMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    public void updateVendor() {
        given(vendorRepository.save(any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().build()));

        Mono<Vendor> vendorMono = Mono.just(Vendor.builder().build());

        webTestClient.put()
                .uri(VendorController.BASE_URL + "/id")
                .body(vendorMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void deleteVendor() {
        given(vendorRepository.findById("id")).willReturn(Mono.empty());

        webTestClient.get()
                .uri(VendorController.BASE_URL + "/id")
                .exchange()
                .expectStatus()
                .isOk();
    }
}
