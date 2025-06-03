package poker.game;

import poker.Card;

import java.util.List;

public class HandsAndBoard {
    private List<Card> hand1;
    private List<Card> hand2;
    private List<Card> board;

    public HandsAndBoard(List<Card> hand1, List<Card> hand2, List<Card> board) {
        this.hand1 = hand1;
        this.hand2 = hand2;
        this.board = board;
    }

    public List<Card> getHand1() {
        return hand1;
    }

    public List<Card> getHand2() {
        return hand2;
    }

    public List<Card> getBoard() {
        return board;
    }
}
