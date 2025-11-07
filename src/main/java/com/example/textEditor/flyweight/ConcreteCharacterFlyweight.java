package com.example.textEditor.flyweight;

public class ConcreteCharacterFlyweight implements CharacterFlyweight {
    private final char symbol;

    public ConcreteCharacterFlyweight(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public void operation(int position) {
        System.out.println("Символ '" + getSymbol() + "' використано на позиції " + position);
    }
}
