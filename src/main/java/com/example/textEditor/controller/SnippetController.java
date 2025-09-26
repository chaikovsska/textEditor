package com.example.textEditor.controller;

import com.example.textEditor.model.Snippet;
import com.example.textEditor.model.Document;
import com.example.textEditor.service.SnippetService;
import com.example.textEditor.service.DocumentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/snippets")
public class SnippetController {

    private final SnippetService snippetService;
    private final DocumentService documentService;

    public SnippetController(SnippetService snippetService, DocumentService documentService) {
        this.snippetService = snippetService;
        this.documentService = documentService;
    }

    @PostMapping
    public Snippet create(@RequestBody Snippet snippet) {
        Document doc = documentService.getById(snippet.getDocument().getId());
        snippet.setDocument(doc);
        return snippetService.create(snippet);
    }

    @GetMapping("/{id}")
    public Snippet getById(@PathVariable int id) {
        return snippetService.getById(id);
    }

    @GetMapping("/by-document/{documentId}")
    public List<Snippet> getByDocument(@PathVariable int documentId) {
        Document doc = documentService.getById(documentId);
        return snippetService.getByDocument(doc);
    }

    @PutMapping("/{id}")
    public Snippet update(@PathVariable int id, @RequestBody Snippet snippet) {
        snippet.setId(id);
        Document doc = documentService.getById(snippet.getDocument().getId());
        snippet.setDocument(doc);
        return snippetService.update(snippet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        snippetService.delete(id);
    }
}