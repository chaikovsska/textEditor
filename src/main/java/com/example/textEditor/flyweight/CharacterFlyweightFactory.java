package com.example.textEditor.flyweight;

import java.util.HashMap;
import java.util.Map;

public class CharacterFlyweightFactory {
    private static final CharacterFlyweightFactory INSTANCE = new CharacterFlyweightFactory();
    private final Map<Character, CharacterFlyweight> pool = new HashMap<>();

    private CharacterFlyweightFactory() {}

    public static CharacterFlyweightFactory getInstance() {
        return INSTANCE;
    }

    public CharacterFlyweight getFlyweight(char symbol) {
        return pool.computeIfAbsent(symbol, ConcreteCharacterFlyweight::new);
    }

    public int getFlyweightCount() {
        return pool.size();
    }

    public Map<Character, CharacterFlyweight> getPool() {
        return pool;
    }
}


