package com.nguyenphucthienan.didemo.controller;

import com.nguyenphucthienan.didemo.service.GreetingServiveImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConstructorInjectedControllerTest {
    private ConstructorInjectedController constructorInjectedController;

    @Before
    public void setUp() {
        this.constructorInjectedController = new ConstructorInjectedController(new GreetingServiveImpl());
    }

    @Test
    public void testGreeting() {
        assertEquals(GreetingServiveImpl.HELLO_SPRING, constructorInjectedController.sayHello());
    }
}
