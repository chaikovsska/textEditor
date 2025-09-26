package com.example.textEditor.repository;

import com.example.textEditor.model.Bookmark;
import com.example.textEditor.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    List<Bookmark> findByDocument(Document document);
}