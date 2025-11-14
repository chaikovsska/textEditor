package com.example.textEditor.service.impl;

import com.example.textEditor.model.Bookmark;
import com.example.textEditor.model.Document;
import com.example.textEditor.repository.BookmarkRepository;
import com.example.textEditor.service.BookmarkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public BookmarkServiceImpl(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @Override
    public Bookmark create(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    @Override
    public Bookmark update(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    @Override
    public void delete(int id) {
        bookmarkRepository.deleteById(id);
    }

}
