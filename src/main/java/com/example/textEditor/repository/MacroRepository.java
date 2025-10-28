package com.example.textEditor.repository;

import com.example.textEditor.model.Macro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MacroRepository extends JpaRepository<Macro, Long> {
    Optional<Macro> findByName(String name);
    Optional<Macro> findByShortcut(String shortcut);
}