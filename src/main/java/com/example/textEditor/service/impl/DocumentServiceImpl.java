package com.example.textEditor.service.impl;

import com.example.textEditor.model.Document;
import com.example.textEditor.repository.DocumentRepository;
import com.example.textEditor.service.DocumentService;
import org.springframework.stereotype.Service;
import com.example.textEditor.strategy.SyntaxHighlightService;

import java.sql.Timestamp;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final SyntaxHighlightService syntaxHighlightService;

    public DocumentServiceImpl(DocumentRepository documentRepository,
                               SyntaxHighlightService syntaxHighlightService) {
        this.documentRepository = documentRepository;
        this.syntaxHighlightService = syntaxHighlightService;
    }

    @Override
    public Document create(Document document) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        document.setCreatedAt(now);
        document.setUpdatedAt(now);

        if (document.getExtension() == null || document.getExtension().isBlank()) {
            detectExtensionByContent(document);
        } else {
            String filename = document.getFilename();
            if (filename == null || filename.isBlank()) {
                filename = "newfile." + document.getExtension();
            } else if (!filename.contains(".")) {
                filename = filename + "." + document.getExtension();
            }
            document.setFilename(filename);
        }

        String highlighted = syntaxHighlightService.highlight(document);
        document.setHighlightedContent(highlighted);

        return documentRepository.save(document);

    }

    @Override
    public Document getById(int id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));

    }

    @Override
    public List<Document> getAll() {
        return documentRepository.findAll();
    }

    @Override
    public Document update(Document document) {
        document.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        detectExtensionByContent(document);

        String highlighted = syntaxHighlightService.highlight(document);
        document.setHighlightedContent(highlighted);

        return documentRepository.save(document);
    }

    @Override
    public void delete(int id) {
        documentRepository.deleteById(id);
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
}