package com.nguyenphucthienan.didemo.service;

import org.springframework.stereotype.Service;

@Service
public class SetterGreetingService implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Hello - Setter";
    }
}
