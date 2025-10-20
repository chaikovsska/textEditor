package com.example.textEditor.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HintGenerator {

    private final List<String> dictionary = new ArrayList<>(Arrays.asList(
            "print", "println", "printf", "for", "while", "if", "else",
            "int", "float", "double", "String", "class", "public", "private", "static"
    ));

    public List<String> generateHints(String input) {
        List<String> hints = new ArrayList<>();
        if (input == null || input.isEmpty()) return hints;
        String lowerInput = input.toLowerCase();
        for (String word : dictionary) {
            if (word.startsWith(lowerInput)) {
                hints.add(word);
            }
        }
        return hints;
    }

}

