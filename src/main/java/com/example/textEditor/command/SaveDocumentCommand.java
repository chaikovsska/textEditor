package com.example.textEditor.command;

import com.example.textEditor.model.Document;
import com.example.textEditor.service.DocumentService;

public class SaveDocumentCommand implements Command {
    private DocumentService documentService;
    private Document document;

    public SaveDocumentCommand(DocumentService documentService, Document document) {
        this.documentService = documentService;
        this.document = document;
    }

    @Override
    public void execute() {
        documentService.create(document);
    }
}

