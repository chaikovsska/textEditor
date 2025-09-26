package com.example.textEditor.service;

import com.example.textEditor.model.Snippet;
import com.example.textEditor.model.Document;

import java.util.List;

public interface SnippetService {
    Snippet create(Snippet snippet);
    Snippet getById(int id);
    List<Snippet> getByDocument(Document document);
    Snippet update(Snippet snippet);
    void delete(int id);
}