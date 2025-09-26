package com.example.textEditor.controller;

import com.example.textEditor.model.Hint;
import com.example.textEditor.model.Document;
import com.example.textEditor.service.HintService;
import com.example.textEditor.service.DocumentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hints")
public class HintController {

    private final HintService hintService;
    private final DocumentService documentService;

    public HintController(HintService hintService, DocumentService documentService) {
        this.hintService = hintService;
        this.documentService = documentService;
    }

    @PostMapping
    public Hint create(@RequestBody Hint hint) {
        Document doc = documentService.getById(hint.getDocument().getId());
        hint.setDocument(doc);
        return hintService.create(hint);
    }

    @GetMapping("/{id}")
    public Hint getById(@PathVariable int id) {
        return hintService.getById(id);
    }

    @GetMapping("/by-document/{documentId}")
    public List<Hint> getByDocument(@PathVariable int documentId) {
        Document doc = documentService.getById(documentId);
        return hintService.getByDocument(doc);
    }

    @PutMapping("/{id}")
    public Hint update(@PathVariable int id, @RequestBody Hint hint) {
        hint.setId(id);
        Document doc = documentService.getById(hint.getDocument().getId());
        hint.setDocument(doc);
        return hintService.update(hint);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        hintService.delete(id);
    }
}