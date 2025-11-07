package com.example.textEditor.flyweight;

public class UnsharedConcreteFlyweight implements CharacterFlyweight {
    private final String complexSymbol;
    private final String color;
    private final boolean bold;
    private final int fontSize;

    public UnsharedConcreteFlyweight(String complexSymbol, String color, boolean bold, int fontSize) {
        this.complexSymbol = complexSymbol;
        this.color = color;
        this.bold = bold;
        this.fontSize = fontSize;
    }

    @Override
    public char getSymbol() {
        return complexSymbol.charAt(0);
    }

    @Override
    public void operation(int position) {
        System.out.println("Нерозділюваний символ \"" + complexSymbol + "\" на позиції " + position
                + " (color=" + color + ", bold=" + bold + ", fontSize=" + fontSize + ")");
    }
}

