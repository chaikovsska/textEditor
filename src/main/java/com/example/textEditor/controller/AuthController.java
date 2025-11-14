package com.example.textEditor.controller;

import com.example.textEditor.model.User;
import com.example.textEditor.security.JWTUtil;
import com.example.textEditor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JWTUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userService.register(user);
        return "OK";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User dbUser = userService.getByUsername(user.getUsername());

        if (dbUser == null || !userService.checkPassword(user.getPassword(), dbUser.getPassword()))
            return "INVALID CREDENTIALS";

        return jwtUtil.generateToken(dbUser.getUsername());
    }
}


