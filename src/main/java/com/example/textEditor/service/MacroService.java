package com.example.textEditor.service;

import com.example.textEditor.template_method.FormattingMacroProcessor;
import com.example.textEditor.template_method.MacroProcessor;
import com.example.textEditor.template_method.SnippetMacroProcessor;
import org.springframework.stereotype.Service;

@Service
public class MacroService {

    public String formatText(String text, String formatType) {
        MacroProcessor processor = new FormattingMacroProcessor();
        return processor.process("@" + formatType + "(" + text + ")");
    }

    public String insertSnippet(String snippetType) {
        MacroProcessor processor = new SnippetMacroProcessor();
        return processor.process("@snippet(" + snippetType + ")");
    }
}


