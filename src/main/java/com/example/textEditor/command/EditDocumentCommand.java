package com.example.textEditor.command;

import com.example.textEditor.model.Document;
import com.example.textEditor.service.DocumentService;

public class EditDocumentCommand implements Command {
    private DocumentService documentService;
    private Document document;
    private String newContent;

    public EditDocumentCommand(DocumentService documentService, Document document, String newContent) {
        this.documentService = documentService;
        this.document = document;
        this.newContent = newContent;
    }

    @Override
    public void execute() {
        document.setContent(newContent);
        documentService.update(document);
    }
}


