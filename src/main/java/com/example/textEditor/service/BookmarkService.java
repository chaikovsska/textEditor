package com.example.textEditor.service;

import com.example.textEditor.model.Bookmark;
import com.example.textEditor.model.Document;

import java.util.List;

public interface BookmarkService {
    Bookmark create(Bookmark bookmark);
    Bookmark update(Bookmark bookmark);
    void delete(int id);
}