package com.nguyenphucthienan.didemo.controller;

import com.nguyenphucthienan.didemo.service.PrimaryGreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropertyInjectedControllerTest {

    private PropertyInjectedController propertyInjectedController;

    @BeforeEach
    public void setUp() {
        propertyInjectedController = new PropertyInjectedController();
        propertyInjectedController.greetingService = new PrimaryGreetingService();
    }

    @Test
    public void testGreeting() {
        assertEquals(PrimaryGreetingService.HELLO_MESSAGE, propertyInjectedController.sayHello());
    }
}
