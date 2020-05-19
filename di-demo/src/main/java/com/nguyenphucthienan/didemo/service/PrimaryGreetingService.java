package com.nguyenphucthienan.didemo.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PrimaryGreetingService implements GreetingService {

    public static final String HELLO_MESSAGE = "Hello - Primary Greeting Service";

    @Override
    public String sayGreeting() {
        return HELLO_MESSAGE;
    }
}
