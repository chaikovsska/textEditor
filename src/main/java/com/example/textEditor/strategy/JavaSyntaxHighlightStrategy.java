package com.example.textEditor.strategy;

import org.springframework.stereotype.Component;

@Component
public class JavaSyntaxHighlightStrategy implements SyntaxHighlightStrategy {

    @Override
    public String highlight(String text) {
        if (text == null) return "";

        text = text.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

        text = text.replaceAll("//.*", "<span class='comment'>$0</span>");

        text = text.replaceAll("\"([^\"]*)\"", "<span class='string'>\"$1\"</span>");

        text = text.replaceAll("\\b(public|class|static|void|int|if|else|for|while)\\b",
                "<span class='keyword'>$1</span>");

        text = text.replaceAll("\\b\\d+\\b", "<span class='number'>$0</span>");

        return text;
    }
}

