package com.example.textEditor.service;

import com.example.textEditor.model.Macros;
import com.example.textEditor.model.Document;

import java.util.List;

public interface MacrosService {
    Macros create(Macros macro);
    Macros getById(int id);
    List<Macros> getByDocument(Document document);
    Macros update(Macros macro);
    void delete(int id);
}