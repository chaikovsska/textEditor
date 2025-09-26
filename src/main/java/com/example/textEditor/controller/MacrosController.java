package com.example.textEditor.controller;

import com.example.textEditor.model.Macros;
import com.example.textEditor.model.Document;
import com.example.textEditor.service.MacrosService;
import com.example.textEditor.service.DocumentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/macros")
public class MacrosController {

    private final MacrosService macrosService;
    private final DocumentService documentService;

    public MacrosController(MacrosService macrosService, DocumentService documentService) {
        this.macrosService = macrosService;
        this.documentService = documentService;
    }

    @PostMapping
    public Macros create(@RequestBody Macros macros) {
        Document doc = documentService.getById(macros.getDocument().getId());
        macros.setDocument(doc);
        return macrosService.create(macros);
    }

    @GetMapping("/{id}")
    public Macros getById(@PathVariable int id) {
        return macrosService.getById(id);
    }

    @GetMapping("/by-document/{documentId}")
    public List<Macros> getByDocument(@PathVariable int documentId) {
        Document doc = documentService.getById(documentId);
        return macrosService.getByDocument(doc);
    }

    @PutMapping("/{id}")
    public Macros update(@PathVariable int id, @RequestBody Macros macros) {
        macros.setId(id);
        Document doc = documentService.getById(macros.getDocument().getId());
        macros.setDocument(doc);
        return macrosService.update(macros);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        macrosService.delete(id);
    }
}