package com.example.textEditor.repository;

import com.example.textEditor.model.Snippet;
import com.example.textEditor.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnippetRepository extends JpaRepository<Snippet, Integer> {
    List<Snippet> findByDocument(Document document);
}