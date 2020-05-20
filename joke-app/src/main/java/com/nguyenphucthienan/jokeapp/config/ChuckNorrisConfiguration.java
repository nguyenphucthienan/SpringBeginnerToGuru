package com.nguyenphucthienan.jokeapp.config;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Bean;

@Configuration
public class ChuckNorrisConfiguration {

    // @Bean
    public ChuckNorrisQuotes chuckNorrisQuotes() {
        return new ChuckNorrisQuotes();
    }
}
