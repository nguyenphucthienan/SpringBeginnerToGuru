package com.nguyenphucthienan.didemo.service;

import org.springframework.stereotype.Component;

@Component
public class GreetingRepositoryImpl implements GreetingRepository {
    @Override
    public String getEnglishGreeting() {
        return "Hello - Primary Greeting service";
    }

    @Override
    public String getSpanishGreeting() {
        return "Hello - Servicio de Saludo Primario";
    }

    @Override
    public String getGermanGreeting() {
        return "Hello - Primärer Dienst";
    }
}
