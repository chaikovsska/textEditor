package com.example.textEditor.repository;

import com.example.textEditor.model.Hint;
import com.example.textEditor.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HintRepository extends JpaRepository<Hint, Integer> {
    List<Hint> findByDocument(Document document);
}