package com.example.textEditor.model;

import jakarta.persistence.*;

@Entity
@Table(name = "macros")
public class Macro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;          // Назва макросу
    private String actions;       // Послідовність дій (у JSON або тексті)
    private String shortcut;      // Комбінація клавіш, наприклад Ctrl+Shift+M

    public Macro() {}

    public Macro(String name, String actions, String shortcut) {
        this.name = name;
        this.actions = actions;
        this.shortcut = shortcut;
    }

    // Гетери / сетери
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getActions() { return actions; }
    public void setActions(String actions) { this.actions = actions; }
    public String getShortcut() { return shortcut; }
    public void setShortcut(String shortcut) { this.shortcut = shortcut; }
}