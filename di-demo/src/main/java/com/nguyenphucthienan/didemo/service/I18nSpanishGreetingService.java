package com.nguyenphucthienan.didemo.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("es")
@Service("i18nService")
public class I18nSpanishGreetingService implements GreetingService {

    private final GreetingRepository greetingRepository;

    public I18nSpanishGreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public String sayGreeting() {
        return greetingRepository.getSpanishGreeting();
    }
}
