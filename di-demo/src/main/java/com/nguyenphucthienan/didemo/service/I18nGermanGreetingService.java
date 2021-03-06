package com.nguyenphucthienan.didemo.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("de")
@Service("i18nService")
public class I18nGermanGreetingService implements GreetingService {

    private final GreetingRepository greetingRepository;

    public I18nGermanGreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public String sayGreeting() {
        return greetingRepository.getGermanGreeting();
    }
}
