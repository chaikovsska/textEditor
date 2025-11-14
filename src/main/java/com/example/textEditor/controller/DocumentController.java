package com.example.textEditor.controller;

import com.example.textEditor.command.*;
import com.example.textEditor.model.Document;
import com.example.textEditor.service.DocumentService;
import com.example.textEditor.strategy.SyntaxHighlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class DocumentController {

    private final DocumentService documentService;
    private final SyntaxHighlightService syntaxHighlightService;
    private final CommandInvoker invoker = new CommandInvoker();

    public DocumentController(DocumentService documentService, SyntaxHighlightService syntaxHighlightService) {
        this.documentService = documentService;
        this.syntaxHighlightService = syntaxHighlightService;
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@RequestBody Document document) {
        try {
            Document saved = documentService.create(document);
            Command command = new SaveDocumentCommand(documentService, saved);
            invoker.executeCommand(command);

            int uniqueCount = documentService.getFlyweightCount();

            Map<String, Object> response = new HashMap<>();
            response.put("document", saved);
            response.put("uniqueSymbols", uniqueCount);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/open/{id}")
    public ResponseEntity<Document> open(@PathVariable int id) {
        try {
            Command command = new OpenDocumentCommand(documentService, id);
            invoker.executeCommand(command);

            Document doc = documentService.getById(id);
            return ResponseEntity.ok(doc);
        } catch (Exception e) {
            Document newDoc = new Document();
            newDoc.setFilename("newfile.txt");
            newDoc.setContent("");
            Document saved = documentService.create(newDoc);

            Command command = new SaveDocumentCommand(documentService, saved);
            invoker.executeCommand(command);

            return ResponseEntity.ok(saved);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> edit(@PathVariable int id, @RequestBody String content) {
        try {
            Document doc = documentService.getById(id);

            Command command = new EditDocumentCommand(documentService, doc, content);
            invoker.executeCommand(command);

            int uniqueCount = documentService.getFlyweightCount();

            Map<String, Object> response = new HashMap<>();
            response.put("document", doc);
            response.put("uniqueSymbols", uniqueCount);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> upload(@RequestParam("file") MultipartFile file) {
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

            Command command = new UploadDocumentCommand(documentService, saved);
            invoker.executeCommand(command);

            int uniqueCount = documentService.getFlyweightCount();

            Map<String, Object> response = new HashMap<>();
            response.put("document", saved);
            response.put("uniqueSymbols", uniqueCount);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/flyweight/stats")
    public ResponseEntity<Map<String, Object>> getFlyweightStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("uniqueSymbols", documentService.getFlyweightCount());
        return ResponseEntity.ok(stats);
    }


    @PostMapping("/highlight")
    public ResponseEntity<String> highlight(@RequestBody Document document) {
        String highlighted = syntaxHighlightService.highlight(document);
        return ResponseEntity.ok(highlighted);
    }

    @GetMapping
    public ResponseEntity<List<Document>> getAll() {
        return ResponseEntity.ok(documentService.getAll());
    }
}


