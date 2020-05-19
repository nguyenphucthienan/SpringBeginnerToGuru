package com.nguyenphucthienan.didemo.controller;

import com.nguyenphucthienan.didemo.service.PrimaryGreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetterInjectedControllerTest {

    private SetterInjectedController setterInjectedController;

    @BeforeEach
    public void setUp() {
        setterInjectedController = new SetterInjectedController();
        setterInjectedController.setGreetingService(new PrimaryGreetingService());
    }

    @Test
    public void testGreeting() {
        assertEquals(PrimaryGreetingService.HELLO_MESSAGE, setterInjectedController.sayHello());

    }
}
