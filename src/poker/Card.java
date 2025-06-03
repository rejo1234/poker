package poker;

import java.util.HashMap;

public class Card {
    int value;
    String suit;
    HashMap<Integer, String> map = new HashMap<>();

    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
        map.put(10, "T");
        map.put(11, "J");
        map.put(12, "Q");
        map.put(13, "K");
        map.put(14, "A");
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    ;

    public String toString() {
        String valueString = map.getOrDefault(value, String.valueOf(value));
        return valueString + suit;
    }

    public void printCard() {
//        System.out.println(value + suit);
//        System.out.print(map.getOrDefault(value, String.valueOf(value)) + suit);
    }
}
