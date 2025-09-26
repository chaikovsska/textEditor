package com.example.textEditor.service.impl;

import com.example.textEditor.model.Macros;
import com.example.textEditor.model.Document;
import com.example.textEditor.repository.MacrosRepository;
import com.example.textEditor.service.MacrosService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MacrosServiceImpl implements MacrosService {

    private final MacrosRepository macrosRepository;

    public MacrosServiceImpl(MacrosRepository macrosRepository) {
        this.macrosRepository = macrosRepository;
    }

    @Override
    public Macros create(Macros macros) {
        return macrosRepository.save(macros);
    }

    @Override
    public Macros getById(int id) {
        return macrosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Macros not found"));
    }

    @Override
    public List<Macros> getByDocument(Document document) {
        return macrosRepository.findByDocument(document);
    }

    @Override
    public Macros update(Macros macros) {
        return macrosRepository.save(macros);
    }

    @Override
    public void delete(int id) {
        macrosRepository.deleteById(id);
    }
}