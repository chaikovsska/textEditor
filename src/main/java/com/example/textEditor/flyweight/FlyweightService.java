package com.example.textEditor.flyweight;

import org.springframework.stereotype.Service;

@Service
public class FlyweightService {
    private final CharacterFlyweightFactory factory = CharacterFlyweightFactory.getInstance();

    public void processText(String text) {
        if (text == null) return;

        int position = 0;
        for (char ch : text.toCharArray()) {
            CharacterFlyweight flyweight = factory.getFlyweight(ch);

            if (flyweight instanceof ConcreteCharacterFlyweight concrete) {
                concrete.operation(position);
                System.out.println("Concrete Flyweight: " + concrete.getSymbol());
            } else {
                flyweight.operation(position);
            }

            position++;
        }

        CharacterFlyweight unshared = new UnsharedConcreteFlyweight("[END]", "red", true, 16);
        unshared.operation(position);
    }

    public int getUniqueSymbolCount() {
        return factory.getFlyweightCount();
    }
}

