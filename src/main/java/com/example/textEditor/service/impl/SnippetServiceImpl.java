package com.example.textEditor.service.impl;

import com.example.textEditor.model.Snippet;
import com.example.textEditor.model.Document;
import com.example.textEditor.repository.SnippetRepository;
import com.example.textEditor.service.SnippetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnippetServiceImpl implements SnippetService {

    private final SnippetRepository snippetRepository;

    public SnippetServiceImpl(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    @Override
    public Snippet create(Snippet snippet) {
        return snippetRepository.save(snippet);
    }

    @Override
    public Snippet getById(int id) {
        return snippetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Snippet not found"));
    }

    @Override
    public List<Snippet> getByDocument(Document document) {
        return snippetRepository.findByDocument(document);
    }

    @Override
    public Snippet update(Snippet snippet) {
        return snippetRepository.save(snippet);
    }

    @Override
    public void delete(int id) {
        snippetRepository.deleteById(id);
    }
}