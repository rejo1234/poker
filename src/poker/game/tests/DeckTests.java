package poker.game.tests;

import poker.Card;
import poker.Deck;
import poker.game.HandsAndBoard;

import java.util.ArrayList;

public class DeckTests extends Deck {
    @Override
    public HandsAndBoard shuffleDeckAndGetHandsAndBoard() {
        ArrayList<Card> hand1 = new ArrayList<>();
        ArrayList<Card> hand2 = new ArrayList<>();
        ArrayList<Card> board = new ArrayList<>();
        hand1.add(new Card(12, "s"));
        hand1.add(new Card(9, "s"));
        hand2.add(new Card(2, "c"));
        hand2.add(new Card(3, "d"));
        board.add(new Card(14, "c"));
        board.add(new Card(13, "c"));
        board.add(new Card(7, "h"));
        board.add(new Card(5, "h"));
        board.add(new Card(4, "d"));
        return new HandsAndBoard(hand1, hand2, board);
    }
}
