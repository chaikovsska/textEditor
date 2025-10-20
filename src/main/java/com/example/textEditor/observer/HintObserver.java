package com.example.textEditor.observer;

import com.example.textEditor.service.impl.HintGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HintObserver implements Observer {

    private final HintGenerator hintGenerator;

    public HintObserver() {
        this.hintGenerator = new HintGenerator();
    }

    @Override
    public void update(String text) {
        List<String> hints = hintGenerator.generateHints(text);
        System.out.println("HintObserver: Підказки для тексту '" + text + "': " + hints);
    }
}
