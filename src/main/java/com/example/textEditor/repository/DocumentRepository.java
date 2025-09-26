package com.example.textEditor.repository;

import com.example.textEditor.model.Document;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

    @EntityGraph(attributePaths = {"bookmarks", "snippets", "hints", "macros"})
    Document findWithAllDependenciesById(int id);
}