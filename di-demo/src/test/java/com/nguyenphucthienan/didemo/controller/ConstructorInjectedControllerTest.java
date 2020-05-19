package com.nguyenphucthienan.didemo.controller;

import com.nguyenphucthienan.didemo.service.PrimaryGreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstructorInjectedControllerTest {

    private ConstructorInjectedController constructorInjectedController;

    @BeforeEach
    public void setUp() {
        constructorInjectedController = new ConstructorInjectedController(new PrimaryGreetingService());
    }

    @Test
    public void testGreeting() {
        assertEquals(PrimaryGreetingService.HELLO_MESSAGE, constructorInjectedController.sayHello());
    }
}
