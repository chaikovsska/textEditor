package com.example.textEditor.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FileSubject implements Subject {

    private static final Logger logger = LoggerFactory.getLogger(FileSubject.class);

    private final List<Observer> observers = new ArrayList<>();
    private String content;

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
        logger.info("Attached observer: {}", observer.getClass().getSimpleName());
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
        logger.info("Detached observer: {}", observer.getClass().getSimpleName());
    }


    @Override
    public void notifyObservers() {
        logger.info("Notifying {} observers...", observers.size());
        for (Observer observer : observers) {
            observer.update(content);
        }
    }
}

