package com.example.textEditor.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bookmark")
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "line_number")
    private int lineNumber;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getLineNumber() { return lineNumber; }
    public void setLineNumber(int lineNumber) { this.lineNumber = lineNumber; }

    public Document getDocument() { return document; }
    public void setDocument(Document document) { this.document = document; }
}