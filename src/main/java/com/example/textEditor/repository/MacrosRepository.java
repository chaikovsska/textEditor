package com.example.textEditor.repository;

import com.example.textEditor.model.Macros;
import com.example.textEditor.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MacrosRepository extends JpaRepository<Macros, Integer> {
    List<Macros> findByDocument(Document document);
}