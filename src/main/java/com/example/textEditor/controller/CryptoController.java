package com.example.textEditor.controller;

import com.example.textEditor.service.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crypto")
@RequiredArgsConstructor
public class CryptoController {

    private final EncryptionService encryptionService;

    @PostMapping("/encrypt")
    public String encrypt(@RequestBody String text) {
        return encryptionService.encrypt(text);
    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestBody String encrypted) {
        return encryptionService.decrypt(encrypted);
    }
}


