package com.example.textEditor.controller;

import com.example.textEditor.service.MacroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/macro")
public class MacroController {

    private final MacroService macroService;

    public MacroController(MacroService macroService) {
        this.macroService = macroService;
    }

    @PostMapping("/format")
    public ResponseEntity<String> formatMacro(
            @RequestParam String text,
            @RequestParam String type
    ) {
        String result = macroService.formatText(text, type);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/snippet")
    public ResponseEntity<String> snippetMacro(
            @RequestParam String type
    ) {
        String result = macroService.insertSnippet(type);
        return ResponseEntity.ok(result);
    }
}



