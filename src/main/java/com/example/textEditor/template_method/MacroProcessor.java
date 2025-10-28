package com.example.textEditor.template_method;

public abstract class MacroProcessor {
    protected String inputText;

    public final String process(String inputText) {
        this.inputText = inputText;
        if (!validateInput(inputText)) {
            throw new IllegalArgumentException("Невалідний макрос");
        }
        String prepared = prepare(inputText);
        String executed = execute(prepared);
        saveResult(executed);
        return executed;
    }

    protected abstract boolean validateInput(String inputText);
    protected abstract String prepare(String inputText);
    protected abstract String execute(String preparedText);
    protected abstract void saveResult(String resultText);
}

