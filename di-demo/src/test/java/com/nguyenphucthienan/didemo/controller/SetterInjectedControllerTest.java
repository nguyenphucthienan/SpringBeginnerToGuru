package com.nguyenphucthienan.didemo.controller;

import com.nguyenphucthienan.didemo.service.GreetingServiveImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SetterInjectedControllerTest {
    private SetterInjectedController setterInjectedController;

    @Before
    public void setUp() {
        this.setterInjectedController= new SetterInjectedController();
        this.setterInjectedController.setGreetingService(new GreetingServiveImpl());
    }

    @Test
    public void testGreeting() {
        assertEquals(GreetingServiveImpl.HELLO_SPRING, setterInjectedController.sayHello());
    }
}
