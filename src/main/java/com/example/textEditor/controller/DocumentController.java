package com.example.textEditor.controller;

import com.example.textEditor.model.Document;
import com.example.textEditor.service.DocumentService;
import com.example.textEditor.strategy.SyntaxHighlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;
    private final SyntaxHighlightService syntaxHighlightService;

    public DocumentController(DocumentService documentService, SyntaxHighlightService syntaxHighlightService) {
        this.documentService = documentService;
        this.syntaxHighlightService = syntaxHighlightService;
    }

    @PostMapping
    public ResponseEntity<Document> create(@RequestBody Document document) {
        return ResponseEntity.ok(documentService.create(document));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> getById(@PathVariable int id) {
        return ResponseEntity.ok(documentService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Document>> getAll() {
        return ResponseEntity.ok(documentService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Document> update(@PathVariable int id, @RequestBody Document document) {
        document.setId(id);
        return ResponseEntity.ok(documentService.update(document));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        documentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<Document> upload(@RequestParam("file") MultipartFile file) {
        try {
            String content = new String(file.getBytes(), java.nio.charset.StandardCharsets.UTF_8);

            String filename = file.getOriginalFilename();
            if (filename == null || filename.isBlank()) {
                return ResponseEntity.badRequest().build();
            }

            Document doc = new Document();
            doc.setFilename(filename);
            doc.setContent(content);

            Document saved = documentService.create(doc);

            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/highlight")
    public ResponseEntity<String> highlight(@RequestBody Document document) {
        String highlighted = syntaxHighlightService.highlight(document);
        return ResponseEntity.ok(highlighted);
    }
}


