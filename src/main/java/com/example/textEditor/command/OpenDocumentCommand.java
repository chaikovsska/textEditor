package com.example.textEditor.command;

import com.example.textEditor.model.Document;
import com.example.textEditor.service.DocumentService;

public class OpenDocumentCommand implements Command {
    private DocumentService documentService;
    private int documentId;

    public OpenDocumentCommand(DocumentService documentService, int documentId) {
        this.documentService = documentService;
        this.documentId = documentId;
    }

    @Override
    public void execute() {
        try {
            Document doc = documentService.getById(documentId);
            System.out.println("Document opened: " + doc.getFilename());
        } catch (Exception e) {
            System.out.println("Document not found. Creating new...");
            Document newDoc = new Document();
            newDoc.setFilename("newfile.txt");
            newDoc.setContent("");
            documentService.create(newDoc);
        }
    }
}

