package com.example.textEditor.template_method;

public class SnippetMacroProcessor extends MacroProcessor {

    private String snippetName;

    @Override
    protected boolean validateInput(String inputText) {
        return inputText.startsWith("@snippet(") && inputText.endsWith(")");
    }

    @Override
    protected String prepare(String inputText) {
        snippetName = inputText.substring(inputText.indexOf("(") + 1, inputText.lastIndexOf(")"));
        return snippetName;
    }

    @Override
    protected String execute(String snippetName) {
        switch (snippetName) {
            case "forloop":
                return "for (int i = 0; i < N; i++) {\n    // код\n}";
            case "class":
                return "class MyClass {\n    private String name;\n    public MyClass(String name) { this.name = name; }\n}";
            case "function":
                return "public void myFunction(int param) {\n    // код\n}";
            default:
                return "// невідомий сніпет: " + snippetName;
        }
    }

    @Override
    protected void saveResult(String resultText) {
        System.out.println("Додано сніпет:\n" + resultText);
    }
}


