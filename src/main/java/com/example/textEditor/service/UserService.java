package com.example.textEditor.service;

import com.example.textEditor.model.User;
import com.example.textEditor.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
    }

    public User getByUsername(String username) {
        return repo.findByUsername(username).orElse(null);
    }

    public boolean checkPassword(String raw, String encoded) {
        return encoder.matches(raw, encoded);
    }
}

