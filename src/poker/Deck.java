package poker;

import poker.game.HandsAndBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Deck {
    public ArrayList<Card> cardList = new ArrayList<>();
    public HashMap<String, Integer> cardIndex = new HashMap<>();

    public Deck() {
        int[] value = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        String[] suit = {"h", "s", "d", "c"};
        int index = 0;
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < suit.length; j++) {
                cardList.add(new Card(value[i], suit[j]));
                if (value[i] <= 9) {
                    cardIndex.put((value[i] + suit[j]), index);
                    index++;
                } else if (value[i] == 10) {
                    cardIndex.put("T" + suit[j], index++);
                } else if (value[i] == 11) {
                    cardIndex.put("J" + suit[j], index++);
                } else if (value[i] == 12) {
                    cardIndex.put("Q" + suit[j], index++);
                } else if (value[i] == 13) {
                    cardIndex.put("K" + suit[j], index++);
                } else if (value[i] == 14) {
                    cardIndex.put("A" + suit[j], index++);
                }
            }
        }
    }

    public HandsAndBoard shuffleDeckAndGetHandsAndBoard() {
        List<Card> possibleRandomBoardAndHands = new ArrayList<>(Deck.this.cardList);
        Random random = new Random();
        List<Card> hand1 = new ArrayList<>();
        List<Card> hand2 = new ArrayList<>();
        List<Card> board = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            int index = random.nextInt(possibleRandomBoardAndHands.size());
            if (i < 2) {
                hand1.add(possibleRandomBoardAndHands.get(index));
                possibleRandomBoardAndHands.remove(index);
            } else if (i < 4) {
                hand2.add(possibleRandomBoardAndHands.get(index));
                possibleRandomBoardAndHands.remove(index);
            } else {
                board.add(possibleRandomBoardAndHands.get(index));
                possibleRandomBoardAndHands.remove(index);
            }
        }
        return new HandsAndBoard(hand1, hand2, board);
    }

    public List<Card> getBoard(String index1, String index2, String index3, String index4, String index5) {
        ArrayList<Card> board = new ArrayList<>();
        int cardIndexBoard1 = cardIndex.get(index1);
        int cardIndexBoard2 = cardIndex.get(index2);
        int cardIndexBoard3 = cardIndex.get(index3);
        int cardIndexBoard4 = cardIndex.get(index4);
        int cardIndexBoard5 = cardIndex.get(index5);
        board.add(cardList.get(cardIndexBoard1));
        board.add(cardList.get(cardIndexBoard2));
        board.add(cardList.get(cardIndexBoard3));
        board.add(cardList.get(cardIndexBoard4));
        board.add(cardList.get(cardIndexBoard5));
        return board;
    }

    public List<Card> getHand(String index1, String index2) {
        ArrayList<Card> hand1 = new ArrayList<>();
        int cardIndexHand1 = cardIndex.get(index1);
        int cardIndexHand2 = cardIndex.get(index2);
        hand1.add(cardList.get(cardIndexHand1));
        hand1.add(cardList.get(cardIndexHand2));
        return hand1;
    }
}