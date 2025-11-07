package com.example.textEditor.service;

import com.example.textEditor.model.Document;
import java.util.List;

public interface DocumentService {
    Document create(Document document);
    Document getById(int id);
    List<Document> getAll();
    Document update(Document document);
    int getFlyweightCount();
}

