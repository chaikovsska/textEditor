package com.example.textEditor.controller;

import com.example.textEditor.service.impl.HintGenerator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/file")
public class HintController {

    private final HintGenerator hintGenerator;

    public HintController(HintGenerator hintGenerator) {
        this.hintGenerator = hintGenerator;
    }

    @PostMapping("/hints")
    public HintsResponse getHints(@RequestBody TextRequest request) {
        List<String> hints = hintGenerator.generateHints(request.getText());
        return new HintsResponse(hints);
    }

    public static class TextRequest {
        private String text;
        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
    }

    public static class HintsResponse {
        private List<String> hints;
        public HintsResponse(List<String> hints) { this.hints = hints; }
    }
}




