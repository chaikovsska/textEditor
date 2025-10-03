package com.example.textEditor.strategy;

public class SyntaxHighlightContext {
    private SyntaxHighlightStrategy strategy;

    public void setStrategy(SyntaxHighlightStrategy strategy) {
        this.strategy = strategy;
    }

    public String applyHighlight(String text) {
        if (strategy == null) return text;
        return strategy.highlight(text);
    }
}

