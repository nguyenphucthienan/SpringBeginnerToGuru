package com.nguyenphucthienan.didemo.service;

import org.springframework.stereotype.Component;

@Component
public class GreetingRepositoryImpl implements GreetingRepository {

    @Override
    public String getEnglishGreeting() {
        return "Hello - English Greeting Service";
    }

    @Override
    public String getSpanishGreeting() {
        return "Hola - Spanish Greeting Service";
    }

    @Override
    public String getGermanGreeting() {
        return "Hallo - German Greeting Service";
    }
}
