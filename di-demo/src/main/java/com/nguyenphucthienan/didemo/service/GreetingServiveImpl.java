package com.nguyenphucthienan.didemo.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiveImpl implements GreetingService {
    public static String HELLO_SPRING = "Hello Spring";

    @Override
    public String sayGreeting() {
        return HELLO_SPRING;
    }
}
