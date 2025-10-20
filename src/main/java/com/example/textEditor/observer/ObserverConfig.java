package com.example.textEditor.observer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObserverConfig {

    @Bean
    public CommandLineRunner registerObservers(FileSubject fileSubject, HintObserver hintObserver) {
        return args -> {
            fileSubject.attach(hintObserver);
        };
    }
}

