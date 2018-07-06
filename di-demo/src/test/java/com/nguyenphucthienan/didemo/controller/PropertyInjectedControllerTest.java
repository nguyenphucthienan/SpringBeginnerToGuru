package com.nguyenphucthienan.didemo.controller;

import com.nguyenphucthienan.didemo.service.GreetingServiveImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PropertyInjectedControllerTest {
    private PropertyInjectedController propertyInjectedController;

    @Before
    public void setUp() {
        this.propertyInjectedController = new PropertyInjectedController();
        this.propertyInjectedController.greetingService = new GreetingServiveImpl();
    }

    @Test
    public void testGreeting() {
        assertEquals(GreetingServiveImpl.HELLO_SPRING, propertyInjectedController.sayHello());
    }
}
