package com.example.textEditor.strategy;

import com.example.textEditor.model.Document;
import org.springframework.stereotype.Service;

@Service
public class SyntaxHighlightService {
    private final SyntaxHighlightContext context = new SyntaxHighlightContext();

    public String highlight(Document doc) {
        String filename = doc.getFilename() != null ? doc.getFilename() : "";

        if (filename.endsWith(".java")) {
            context.setStrategy(new JavaSyntaxHighlightStrategy());
        } else {
            context.setStrategy(new PlainTextSyntaxHighlightStrategy());
        }
        return context.applyHighlight(doc.getContent());
    }
}

