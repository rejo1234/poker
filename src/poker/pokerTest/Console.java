package poker.pokerTest;

import java.util.ArrayList;

public class Console {
    public static void main(String[] args) throws Exception {
        System.out.println("jade z konsoli");
        boolean isBoard = false;
        String board = "";
        ArrayList<String> handsStr = new ArrayList<>();
        ArrayList<Hand> hands = new ArrayList<>();

        for (String s : args) {
            if (s.equals("-b")) {
                isBoard = true;
                continue;
            }


            if (isBoard) {
                board = s;
                isBoard = false;
            } else {
                handsStr.add(s);
            }
        }
        EquityCalculator calculator = new EquityCalculator();
        if (!board.isEmpty()) {
            calculator.setBoardFromString(board);
        }

//		for(int i = 0; i < handsStr.size(); i++) {
//			Hand h = Hand.fromString(handsStr.get(i));
//			hands.add(h);
//			calculator.addHand(h);
//		}
        //        List<Card> hand = myDeck.getHand("Ah", "Kd"); Equity gracza1  0.47189751352563564 Labs 45
//        List<Card> hand2 = myDeck.getHand("6s", "6h");Equity gracza2  0.5281024864743644 Labs 55

//        List<Card> hand = myDeck.getHand("Jh", "Td"); Equity gracza1  0.6800731645782525 Labs 73,4
//        List<Card> hand2 = myDeck.getHand("9s", "Th");Equity gracza2  0.31992683542174755 Labs 26,6

        //      List<Card> hand = myDeck.getHand("Ah", "Kd");Equity gracza1  0.6109890533456677 Labs 63,1
        //     List<Card> hand2 = myDeck.getHand("9s", "Th");Equity gracza2  0.3890109466543324 Labs 36.9
        Hand hand1 = Hand.fromString("AhKd");
        Hand hand2 = Hand.fromString("6s6h");
        hands.add(hand1);
        hands.add(hand2);
        calculator.addHand(hand1);
        calculator.addHand(hand2);


        long startTime = System.currentTimeMillis();
        calculator.calculate();
        long elapsedTime = System.currentTimeMillis() - startTime;


        calculator.printBoard();
        System.out.println("");


        for (int i = 0; i < hands.size(); i++) {
            HandRanking hr = calculator.getHandRanking(i);
            HandEquity he = calculator.getHandEquity(i);

            String preprend = calculator.boardIsEmpty() ? "~" : "";

            System.out.println(String.format("Player %d: %s - %s --- %s%s", 1 + i, hands.get(i), hr, preprend, he));
        }


        if (calculator.boardIsEmpty()) {
            float elapsedSeconds = elapsedTime / 1000.0f;

            System.out.println("");
            System.out.println(String.format("Simulated %d random boards in %.1f seconds", calculator.getMaxIterations(), elapsedSeconds));
        }
    }
}
