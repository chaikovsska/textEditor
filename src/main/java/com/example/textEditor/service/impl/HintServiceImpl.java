package com.example.textEditor.service.impl;

import com.example.textEditor.model.Hint;
import com.example.textEditor.model.Document;
import com.example.textEditor.repository.HintRepository;
import com.example.textEditor.service.HintService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HintServiceImpl implements HintService {

    private final HintRepository hintRepository;

    public HintServiceImpl(HintRepository hintRepository) {
        this.hintRepository = hintRepository;
    }

    @Override
    public Hint create(Hint hint) {
        return hintRepository.save(hint);
    }

    @Override
    public Hint getById(int id) {
        return hintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hint not found"));
    }

    @Override
    public List<Hint> getByDocument(Document document) {
        return hintRepository.findByDocument(document);
    }

    @Override
    public Hint update(Hint hint) {
        return hintRepository.save(hint);
    }

    @Override
    public void delete(int id) {
        hintRepository.deleteById(id);
    }
}