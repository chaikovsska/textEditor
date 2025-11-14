package com.example.textEditor.controller;

import com.example.textEditor.model.Bookmark;
import com.example.textEditor.model.Document;
import com.example.textEditor.service.BookmarkService;
import com.example.textEditor.service.DocumentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final DocumentService documentService;

    public BookmarkController(BookmarkService bookmarkService, DocumentService documentService) {
        this.bookmarkService = bookmarkService;
        this.documentService = documentService;
    }

    @PostMapping
    public Bookmark create(@RequestBody Bookmark bookmark) {
        Document doc = documentService.getById(bookmark.getDocument().getId());
        bookmark.setDocument(doc);
        return bookmarkService.create(bookmark);
    }

    @PutMapping("/{id}")
    public Bookmark update(@PathVariable int id, @RequestBody Bookmark bookmark) {
        bookmark.setId(id);
        Document doc = documentService.getById(bookmark.getDocument().getId());
        bookmark.setDocument(doc);
        return bookmarkService.update(bookmark);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        bookmarkService.delete(id);
    }
}

