package com.example.textEditor.service.impl;

import com.example.textEditor.flyweight.FlyweightService;
import com.example.textEditor.model.Document;
import com.example.textEditor.repository.DocumentRepository;
import com.example.textEditor.service.DocumentService;
import com.example.textEditor.service.EncryptionService;
import com.example.textEditor.strategy.SyntaxHighlightService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final SyntaxHighlightService syntaxHighlightService;
    private final FlyweightService flyweightService;
    private final EncryptionService encryptionService;

    public DocumentServiceImpl(
            DocumentRepository documentRepository,
            SyntaxHighlightService syntaxHighlightService,
            FlyweightService flyweightService,
            EncryptionService encryptionService
    ) {
        this.documentRepository = documentRepository;
        this.syntaxHighlightService = syntaxHighlightService;
        this.flyweightService = flyweightService;
        this.encryptionService = encryptionService;
    }

    @Override
    public Document create(Document document) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        document.setCreatedAt(now);
        document.setUpdatedAt(now);

        String plainText = document.getContent();

        if (plainText != null && !plainText.isEmpty()) {
            flyweightService.processText(plainText);
        }

        detectExtensionByContent(document);

        String highlighted = syntaxHighlightService.highlight(document);
        document.setHighlightedContent(highlighted);

        if (plainText != null) {
            String encrypted = encryptionService.encrypt(plainText);
            document.setContent(encrypted);
        }

        return documentRepository.save(document);
    }

    @Override
    public Document getById(int id) {
        Document doc = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        String decrypted = encryptionService.decrypt(doc.getContent());
        doc.setContent(decrypted);

        String highlighted = syntaxHighlightService.highlight(doc);
        doc.setHighlightedContent(highlighted);

        return doc;
    }

    @Override
    public List<Document> getAll() {
        List<Document> docs = documentRepository.findAll();

        for (Document doc : docs) {
            try {
                String decrypted = encryptionService.decrypt(doc.getContent());
                doc.setContent(decrypted);
            } catch (Exception ignored) {
            }
        }
        return docs;
    }

    @Override
    public Document update(Document document) {
        document.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        String plainText = document.getContent();

        if (plainText != null && !plainText.isEmpty()) {
            flyweightService.processText(plainText);
        }

        detectExtensionByContent(document);

        String highlighted = syntaxHighlightService.highlight(document);
        document.setHighlightedContent(highlighted);

        String encrypted = encryptionService.encrypt(plainText);
        document.setContent(encrypted);

        return documentRepository.save(document);
    }

    private void detectExtensionByContent(Document document) {
        String content = document.getContent() == null ? "" : document.getContent();
        String extension = detectByRegex(content);

        document.setExtension(extension);

        String filename = document.getFilename();
        if (filename == null || filename.isBlank()) {
            filename = "newfile." + extension;
        } else if (!filename.contains(".")) {
            filename = filename + "." + extension;
        } else {
            int dotIndex = filename.lastIndexOf('.');
            filename = filename.substring(0, dotIndex + 1) + extension;
        }
        document.setFilename(filename);
    }

    private String detectByRegex(String content) {
        String lower = content.toLowerCase();

        if (lower.contains("public class")
                || lower.contains("class ")
                || lower.contains("package ")
                || lower.contains("import java")
                || lower.contains("system.out.println")) {
            return "java";
        }

        if (lower.contains("function ")
                || lower.contains("console.log")
                || lower.contains("=>")
                || lower.contains("document.getelementbyid")) {
            return "js";
        }

        if (lower.contains("def ")
                || lower.contains("import ")
                || lower.contains("self")
                || lower.contains("print(")) {
            return "py";
        }

        if (lower.matches("(?s).*\\.[a-z0-9\\-]+\\s*\\{.*\\}.*")) {
            return "css";
        }

        if (lower.trim().startsWith("<?xml") || lower.contains("<xml")) {
            return "xml";
        }

        if (lower.trim().startsWith("{") && lower.trim().endsWith("}")) {
            return "json";
        }

        if (lower.contains("<!doctype html") || lower.contains("<html")) {
            return "html";
        }

        return "txt";
    }

    public int getFlyweightCount() {
        return flyweightService.getUniqueSymbolCount();
    }
}