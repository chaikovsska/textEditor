package com.example.textEditor.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hint")
public class Hint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    private String text;

    @Column(name = "line_number")
    private int lineNumber;

    @Column(name = "trigger_word")
    private String triggerWord;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public int getLineNumber() { return lineNumber; }
    public void setLineNumber(int lineNumber) { this.lineNumber = lineNumber; }

    public String getTriggerWord() { return triggerWord; }
    public void setTriggerWord(String triggerWord) { this.triggerWord = triggerWord; }

    public Document getDocument() { return document; }
    public void setDocument(Document document) { this.document = document; }
}