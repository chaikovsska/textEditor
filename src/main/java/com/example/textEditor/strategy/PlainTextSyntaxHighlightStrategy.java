package com.example.textEditor.strategy;

public class PlainTextSyntaxHighlightStrategy implements SyntaxHighlightStrategy {
    @Override
    public String highlight(String text) {
        return text;
    }
}

