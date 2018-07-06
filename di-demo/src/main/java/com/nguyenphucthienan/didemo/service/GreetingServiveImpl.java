package com.nguyenphucthienan.didemo.service;

public class GreetingServiveImpl implements GreetingService {
    public static String HELLO_SPRING = "Hello Spring";

    @Override
    public String sayGreeting() {
        return HELLO_SPRING;
    }
}
