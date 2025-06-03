package poker;

import java.util.List;

public class PokerTest {
    Deck deck;
    EquityEvaluator equityEvaluator;

    public PokerTest(EquityEvaluator equityEvaluator) {
        this.deck = new Deck();
        this.equityEvaluator = equityEvaluator;
    }

    public void test() {
        System.out.println("testuje straight flusha");
        testStraightFlush();
        System.out.println("testuje Quadsa");
        testQuads();
        System.out.println("testuje fulla");
        testFull();
        System.out.println("testuje kolor");
        testFlush();
        System.out.println("testuje straighta");
        testStraight();
        System.out.println("testuje tripsa");
        testTrips();
        System.out.println("testuje two pair");
        testTwoPair();
        System.out.println("testuje pare");
        testPair();
    }

    public void testStraightFlush() {
        List<List<Card>> boardList = List.of(deck.getBoard("As", "Qs", "Js", "5s", "9d"),
                deck.getBoard("6s", "5s", "7s", "8s", "9s"),
                deck.getBoard("2s", "3s", "4s", "5s", "9h"));
        List<List<Card>> handList = List.of(deck.getHand("Ts", "Ks"), deck.getHand("8h", "7h"), deck.getHand("As", "7h"));
        for (int i = 0; i < boardList.size(); i++) {
            ResultHandOut result1 = equityEvaluator.winningHand(handList.get(i), boardList.get(i));
            if (result1.getValue() == 1) {
                System.out.println("Jest Straight flush");
            } else {
                System.out.println("NIE MA Straight flush");
            }
        }
    }

    public void testQuads() {
        List<List<Card>> boardList = List.of(deck.getBoard("4h", "4s", "4c", "4d", "9d"),
                deck.getBoard("3s", "3s", "3s", "8s", "9s"),
                deck.getBoard("3s", "3s", "4s", "5s", "9h"));
        List<List<Card>> handList = List.of(deck.getHand("Ts", "Ks"), deck.getHand("3h", "7h"), deck.getHand("3s", "3h"));
        for (int i = 0; i < boardList.size(); i++) {
            ResultHandOut result1 = equityEvaluator.winningHand(handList.get(i), boardList.get(i));
            if (result1.getValue() == 2) {
                System.out.println("Jest Quads");
            } else {
                System.out.println("NIE MA Quadsa");
            }
        }
    }

    public void testFull() {
        List<List<Card>> boardList = List.of(deck.getBoard("4h", "4s", "4c", "Td", "Th"),
                deck.getBoard("Ts", "Td", "8h", "8s", "9s"),
                deck.getBoard("3d", "As", "4s", "3s", "9h"),
                deck.getBoard("Ts", "Td", "Th", "8s", "9s"));
        List<List<Card>> handList = List.of(deck.getHand("Qs", "Ks"), deck.getHand("Th", "7h"), deck.getHand("Ac", "Ah"), deck.getHand("8c", "8h"));
        for (int i = 0; i < boardList.size(); i++) {
            ResultHandOut result1 = equityEvaluator.winningHand(handList.get(i), boardList.get(i));
            if (result1.getValue() == 3) {
                System.out.println("Jest full");
            } else {
                System.out.println("NIE MA fulla");
            }
        }
    }

    public void testFlush() {
        List<List<Card>> boardList = List.of(deck.getBoard("4h", "5h", "8h", "Th", "Qh"),
                deck.getBoard("Th", "Jh", "5h", "8h", "9s"),
                deck.getBoard("3h", "6h", "Ah", "5s", "9d"));
        List<List<Card>> handList = List.of(deck.getHand("Ts", "Ks"), deck.getHand("3h", "7s"), deck.getHand("Jh", "4h"));
        for (int i = 0; i < boardList.size(); i++) {
            ResultHandOut result1 = equityEvaluator.winningHand(handList.get(i), boardList.get(i));
            if (result1.getValue() == 4) {
                System.out.println("Jest flush");
            } else {
                System.out.println("NIE MA flush");
            }
        }
    }

    public void testStraight() {
        List<List<Card>> boardList = List.of(deck.getBoard("As", "Qd", "Jh", "Ks", "Td"),
                deck.getBoard("6s", "Qd", "7h", "8c", "9s"),
                deck.getBoard("2s", "3h", "4c", "8s", "9h"),
                deck.getBoard("2s", "3h", "4c", "8s", "9h"));
        List<List<Card>> handList = List.of(deck.getHand("Ts", "Ks"), deck.getHand("Th", "7h"), deck.getHand("6s", "5h"), deck.getHand("As", "5h"));
        for (int i = 0; i < boardList.size(); i++) {
            ResultHandOut result1 = equityEvaluator.winningHand(handList.get(i), boardList.get(i));
            if (result1.getValue() == 5) {
                System.out.println("Jest Straight");
            } else {
                System.out.println("NIE MA Straight");
            }
        }
    }

    public void testTrips() {
        List<List<Card>> boardList = List.of(deck.getBoard("As", "Ac", "Ah", "5s", "9d"),
                deck.getBoard("Ac", "Ah", "7d", "8s", "9s"),
                deck.getBoard("2s", "3s", "4s", "7c", "9h"));
        List<List<Card>> handList = List.of(deck.getHand("Ts", "Ks"), deck.getHand("As", "2h"), deck.getHand("7s", "7h"));
        for (int i = 0; i < boardList.size(); i++) {
            ResultHandOut result1 = equityEvaluator.winningHand(handList.get(i), boardList.get(i));
            if (result1.getValue() == 6) {
                System.out.println("Jest trips");
            } else {
                System.out.println("NIE MA tripsa");
            }
        }
    }

    public void testTwoPair() {
        List<List<Card>> boardList = List.of(deck.getBoard("4h", "9s", "4c", "Ad", "9d"),
                deck.getBoard("3s", "3h", "2s", "8s", "Ts"),
                deck.getBoard("3s", "8s", "4s", "5s", "9h"),
                deck.getBoard("3s", "3h", "4s", "5s", "9h"));
        List<List<Card>> handList = List.of(deck.getHand("Ts", "Ks"), deck.getHand("2h", "7h"), deck.getHand("5c", "8h"), deck.getHand("8c", "8h"));
        for (int i = 0; i < boardList.size(); i++) {
            ResultHandOut result1 = equityEvaluator.winningHand(handList.get(i), boardList.get(i));
            if (result1.getValue() == 7) {
                System.out.println("Jest dwie pary");
            } else {
                System.out.println("NIE MA dwie pary");
            }
        }
    }

    public void testPair() {
        List<List<Card>> boardList = List.of(deck.getBoard("4h", "As", "Kc", "9d", "4c"),
                deck.getBoard("Ts", "Ad", "2h", "3s", "9s"),
                deck.getBoard("3d", "As", "4s", "7s", "9h"));
        List<List<Card>> handList = List.of(deck.getHand("Qs", "Js"), deck.getHand("Th", "7h"), deck.getHand("Jc", "Jh"));
        for (int i = 0; i < boardList.size(); i++) {
            ResultHandOut result1 = equityEvaluator.winningHand(handList.get(i), boardList.get(i));
            if (result1.getValue() == 8) {
                System.out.println("Jest para");
            } else {
                System.out.println("NIE MA pary");
            }
        }
    }
}
