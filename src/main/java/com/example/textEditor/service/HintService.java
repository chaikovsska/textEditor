package com.example.textEditor.service;

import com.example.textEditor.model.Hint;
import com.example.textEditor.model.Document;

import java.util.List;

public interface HintService {
    Hint create(Hint hint);
    Hint getById(int id);
    List<Hint> getByDocument(Document document);
    Hint update(Hint hint);
    void delete(int id);
}