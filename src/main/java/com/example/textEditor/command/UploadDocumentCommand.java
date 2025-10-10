package com.example.textEditor.command;

import com.example.textEditor.model.Document;
import com.example.textEditor.service.DocumentService;

public class UploadDocumentCommand implements Command {

    private final DocumentService documentService;
    private final Document document;

    public UploadDocumentCommand(DocumentService documentService, Document document) {
        this.documentService = documentService;
        this.document = document;
    }

    @Override
    public void execute() {
        documentService.create(document);
    }
}

