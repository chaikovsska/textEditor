package com.example.textEditor.template_method;

public class FormattingMacroProcessor extends MacroProcessor {

    private String command;

    @Override
    protected boolean validateInput(String inputText) {
        return inputText.startsWith("@bold(") ||
                inputText.startsWith("@italic(") ||
                inputText.startsWith("@uppercase(") ||
                inputText.startsWith("@lowercase(");
    }

    @Override
    protected String prepare(String inputText) {
        int start = 1;
        int end = inputText.indexOf("(");
        command = inputText.substring(start, end);
        return inputText.substring(end + 1, inputText.lastIndexOf(")"));
    }

    @Override
    protected String execute(String text) {
        switch (command) {
            case "bold": return "<b>" + text + "</b>";
            case "italic": return "<i>" + text + "</i>";
            case "uppercase": return text.toUpperCase();
            case "lowercase": return text.toLowerCase();
            default: return text;
        }
    }

    @Override
    protected void saveResult(String resultText) {
        System.out.println("Відформатовано: " + resultText);
    }
}



