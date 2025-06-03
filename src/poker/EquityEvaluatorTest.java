package poker;

import poker.pokerTest.CardRank;
import poker.pokerTest.HandRanking;

import java.util.*;
import java.util.stream.Collectors;

import static poker.pokerTest.Card.Suit.*;
import static poker.pokerTest.HandRanking.Ranking.*;

public class EquityEvaluatorTest {
    public final List<Card> hand;
    public final List<Card> hand2;
    Deck deck;
    private List<Card> board;

    public EquityEvaluatorTest(Deck deck, List<Card> kekw, List<Card> hand2, List<Card> board) {
        this.deck = deck;
        this.hand = kekw;
        this.hand2 = hand2;
        this.board = board;
    }

    public void calculateEquityTest() {
        List<Card> deckWithoutHandAndBoard = new ArrayList<>(deck.cardList);
        deckWithoutHandAndBoard.removeAll(hand);
        deckWithoutHandAndBoard.removeAll(hand2);
        int winsPlayer1 = 0;
        int winsPlayer2 = 0;
        int ties = 0;
        int index = 0;

        HashMap<Integer, List<Integer>> testIndexListMap = new HashMap<>();
        testIndexListMap.put(1, List.of(0, 1, 2, 3, 4));
        testIndexListMap.put(2, List.of(0, 0, 1, 2, 3));
        testIndexListMap.put(3, List.of(0, 0, 1, 1, 2));
        testIndexListMap.put(4, List.of(0, 0, 0, 1, 2));
        testIndexListMap.put(5, List.of(0, 1, 2, 3, 4));
        testIndexListMap.put(6, List.of(0, 1, 2, 3, 4));
        testIndexListMap.put(7, List.of(0, 0, 0, 1, 1));
        testIndexListMap.put(8, List.of(0, 0, 0, 0, 1));
        testIndexListMap.put(9, List.of(0, 1, 2, 3, 4));
        HashMap<Integer, HandRanking.Ranking> handsValue = new HashMap<>();
        handsValue.put(1, HIGH_CARD);
        handsValue.put(2, HandRanking.Ranking.PAIR);
        handsValue.put(3, TWO_PAIRS);
        handsValue.put(4, TRIPS);
        handsValue.put(5, STRAIGHT);
        handsValue.put(6, FLUSH);
        handsValue.put(7, HandRanking.Ranking.FULL_HOUSE);
        handsValue.put(8, HandRanking.Ranking.QUADS);
        handsValue.put(9, STRAIGHT_FLUSH);
        Map<String, poker.pokerTest.Card.Suit> mapSuit = new HashMap<>();
        mapSuit.put("d", DIAMOND);
        mapSuit.put("h", HEART);
        mapSuit.put("s", SPADE);
        mapSuit.put("c", CLUB);
        for (int i = 0; i < deckWithoutHandAndBoard.size(); i++) {
            for (int j = i + 1; j < deckWithoutHandAndBoard.size(); j++) {
                for (int k = j + 1; k < deckWithoutHandAndBoard.size(); k++) {
                    for (int m = k + 1; m < deckWithoutHandAndBoard.size(); m++) {
                        for (int n = m + 1; n < deckWithoutHandAndBoard.size(); n++) {
                            List<Card> possibleBoard = new ArrayList<>();
                            possibleBoard.add(deckWithoutHandAndBoard.get(i));
                            possibleBoard.add(deckWithoutHandAndBoard.get(j));
                            possibleBoard.add(deckWithoutHandAndBoard.get(k));
                            possibleBoard.add(deckWithoutHandAndBoard.get(m));
                            possibleBoard.add(deckWithoutHandAndBoard.get(n));
                            index++;
                            Card board1 = possibleBoard.get(0);
                            Card board2 = possibleBoard.get(1);
                            Card board3 = possibleBoard.get(2);
                            Card board4 = possibleBoard.get(3);
                            Card board5 = possibleBoard.get(4);
                            Card h1a = hand.get(0);
                            Card h1b = hand.get(1);
                            ResultHandOut resultPlayer1 = winningHand(hand, possibleBoard);
                            ResultHandOut resultPlayer2 = winningHand(hand2, board);
                            poker.pokerTest.Card otherPlayerCard1a = new poker.pokerTest.Card(mapSuit.get(h1a.getSuit()), new CardRank(h1a.getValue()));
                            poker.pokerTest.Card otherPlayerCard1b = new poker.pokerTest.Card(mapSuit.get(h1b.getSuit()), new CardRank(h1b.getValue()));
                            poker.pokerTest.Card otherCard1 = new poker.pokerTest.Card(mapSuit.get(board1.getSuit()), new CardRank(board1.getValue()));
                            poker.pokerTest.Card otherCard2 = new poker.pokerTest.Card(mapSuit.get(board2.getSuit()), new CardRank(board2.getValue()));
                            poker.pokerTest.Card otherCard3 = new poker.pokerTest.Card(mapSuit.get(board3.getSuit()), new CardRank(board3.getValue()));
                            poker.pokerTest.Card otherCard4 = new poker.pokerTest.Card(mapSuit.get(board4.getSuit()), new CardRank(board4.getValue()));
                            poker.pokerTest.Card otherCard5 = new poker.pokerTest.Card(mapSuit.get(board5.getSuit()), new CardRank(board5.getValue()));
                            HandRanking handRanking = HandRanking.evaluate(otherPlayerCard1a, otherPlayerCard1b, otherCard1, otherCard2, otherCard3, otherCard4, otherCard5);
                            List<Integer> player1Values = resultPlayer1.getBestHandValues();
                            List<Integer> player1ValuesTest = handRanking.getHighCardsRanks().stream()
                                    .map(cardRank -> cardRank.getValue())
                                    .collect(Collectors.toList());

                            if (resultPlayer1.getValue() != 3 && resultPlayer1.getValue() != 7) {
                                if (handsValue.get(resultPlayer1.getValue()).equals(handRanking.getRank())) {
                                    for (int p = 0; p < 5; p++) {
                                        int testIndex = testIndexListMap.get(resultPlayer1.getValue()).get(p);
                                        if (!player1Values.get(p).equals(player1ValuesTest.get(testIndex))) {
                                            System.out.println(index);
                                            System.out.println(resultPlayer1.getBestHandValues());
                                            System.out.println(handRanking.getHighCardsRanks());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void calculateEquity() {
        List<Card> deckWithoutHandAndBoard = new ArrayList<>(deck.cardList);
        deckWithoutHandAndBoard.removeAll(hand);
        deckWithoutHandAndBoard.removeAll(hand2);
        int winsPlayer1 = 0;
        int winsPlayer2 = 0;
        int ties = 0;

        HashMap<Integer, HandRanking.Ranking> handsValue = new HashMap<>();
        handsValue.put(1, HIGH_CARD);
        handsValue.put(2, HandRanking.Ranking.PAIR);
        handsValue.put(3, TWO_PAIRS);
        handsValue.put(4, TRIPS);
        handsValue.put(5, STRAIGHT);
        handsValue.put(6, FLUSH);
        handsValue.put(7, HandRanking.Ranking.FULL_HOUSE);
        handsValue.put(8, HandRanking.Ranking.QUADS);
        handsValue.put(9, STRAIGHT_FLUSH);
        Map<String, poker.pokerTest.Card.Suit> mapSuit = new HashMap<>();
        mapSuit.put("d", DIAMOND);
        mapSuit.put("h", HEART);
        mapSuit.put("s", SPADE);
        mapSuit.put("c", CLUB);

        int index2 = 0;
        for (int i = 0; i < deckWithoutHandAndBoard.size(); i++) {
            for (int j = i + 1; j < deckWithoutHandAndBoard.size(); j++) {
                for (int k = j + 1; k < deckWithoutHandAndBoard.size(); k++) {
                    for (int m = k + 1; m < deckWithoutHandAndBoard.size(); m++) {
                        for (int n = m + 1; n < deckWithoutHandAndBoard.size(); n++) {
                            List<Card> possibleBoard = new ArrayList<>();
                            possibleBoard.add(deckWithoutHandAndBoard.get(i));
                            possibleBoard.add(deckWithoutHandAndBoard.get(j));
                            possibleBoard.add(deckWithoutHandAndBoard.get(k));
                            possibleBoard.add(deckWithoutHandAndBoard.get(m));
                            possibleBoard.add(deckWithoutHandAndBoard.get(n));
                            Card board1 = possibleBoard.get(0);
                            Card board2 = possibleBoard.get(1);
                            Card board3 = possibleBoard.get(2);
                            Card board4 = possibleBoard.get(3);
                            Card board5 = possibleBoard.get(4);
                            Card h1a = hand.get(0);
                            Card h1b = hand.get(1);
                            ResultHandOut resultPlayer1 = winningHand(hand, possibleBoard);
                            ResultHandOut resultPlayer2 = winningHand(hand2, possibleBoard);

                            poker.pokerTest.Card otherPlayerCard1a = new poker.pokerTest.Card(mapSuit.get(h1a.getSuit()), new CardRank(h1a.getValue()));
                            poker.pokerTest.Card otherPlayerCard1b = new poker.pokerTest.Card(mapSuit.get(h1b.getSuit()), new CardRank(h1b.getValue()));
                            poker.pokerTest.Card otherCard1 = new poker.pokerTest.Card(mapSuit.get(board1.getSuit()), new CardRank(board1.getValue()));
                            poker.pokerTest.Card otherCard2 = new poker.pokerTest.Card(mapSuit.get(board2.getSuit()), new CardRank(board2.getValue()));
                            poker.pokerTest.Card otherCard3 = new poker.pokerTest.Card(mapSuit.get(board3.getSuit()), new CardRank(board3.getValue()));
                            poker.pokerTest.Card otherCard4 = new poker.pokerTest.Card(mapSuit.get(board4.getSuit()), new CardRank(board4.getValue()));
                            poker.pokerTest.Card otherCard5 = new poker.pokerTest.Card(mapSuit.get(board5.getSuit()), new CardRank(board5.getValue()));
                            HandRanking handRanking = HandRanking.evaluate(otherPlayerCard1a, otherPlayerCard1b, otherCard1, otherCard2, otherCard3, otherCard4, otherCard5);
                            if (resultPlayer1.getValue() > resultPlayer2.getValue()) {
                                winsPlayer1++;
                            } else if (resultPlayer1.getValue() < resultPlayer2.getValue()) {
                                winsPlayer2++;
                            } else {
                                List<Integer> hand1Cards = resultPlayer1.getBestHandValues();
                                List<Integer> hand2Cards = resultPlayer2.getBestHandValues();
                                int checkStrongerHand1 = 0;
                                int checkStrongerHand2 = 0;
                                for (int y = 0; y < hand1Cards.size(); y++) {
                                    if (hand1Cards.get(y) > hand2Cards.get(y)) {
                                        winsPlayer1++;
                                        break;
                                    } else if (hand1Cards.get(y) < hand2Cards.get(y)) {
                                        winsPlayer2++;
                                        break;
                                    } else if (y == 4) {
                                        ties++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        int total = winsPlayer1 + winsPlayer2 + ties;
        double equityPlayer1 = (double) winsPlayer1 / total;
        double equityPlayer2 = (double) winsPlayer2 / total;
        double equityFromTies = (double) ties / total;
        double finalEquityPlayer1 = equityPlayer1 + equityFromTies / 2;
        double finalEquityPlayer2 = equityPlayer2 + equityFromTies / 2;
        System.out.println("Equity gracza1  " + finalEquityPlayer1);
        System.out.println("Equity gracza2  " + finalEquityPlayer2);
    }

    public void countTime() {
        long startTime = System.currentTimeMillis();

        calculateEquity();

        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;
        System.out.println("Zajeło to" + duration);
    }

    public void decisionWhoWins(ResultHandOut bestHand, ResultHandOut bestHand2, HashMap<Integer, String> arrangements) {
        if (bestHand.getValue() > bestHand2.getValue()) {
            System.out.println("gracz2 wygrał z" + arrangements.get(bestHand2.getValue()));
        } else if (bestHand.getValue() < bestHand2.getValue()) {
            System.out.println("gracz1 wygrał z" + arrangements.get(bestHand.getValue()));
        } else {
            List<Integer> hand1Cards = bestHand.getBestHandValues();
            List<Integer> hand2Cards = bestHand2.getBestHandValues();

            for (int i = 0; i < hand1Cards.size(); i++) {
                if (hand1Cards.get(i) > hand2Cards.get(i)) {
                    System.out.println("wygrał gracz1" + arrangements.get(bestHand.getValue()));
                    return;
                } else if (hand1Cards.get(i) < hand2Cards.get(i)) {
                    System.out.println("wygrał gracz2" + arrangements.get(bestHand2.getValue()));
                    return;
                }
            }
            System.out.println("gracze remisują" + arrangements.get(bestHand2.getValue()));
        }

    }

    public List<Integer> checkStraightFlush(List<Card> handPlusBoard) {
        List<Integer> listFlushValue = checkFlush(handPlusBoard);
        List<Integer> straightFlushList = new ArrayList<>();
        int streakStraightFlush = 1;
        if (listFlushValue.size() >= 5) {
            for (int i = listFlushValue.size() - 1; i > 0; i--) {
                if (listFlushValue.get(i - 1) - listFlushValue.get(i) == 1) {
                    streakStraightFlush++;
                    straightFlushList.add(listFlushValue.get(i));
                } else {
                    streakStraightFlush = 1;
                    straightFlushList.clear();
                }
                if (i == 1 && listFlushValue.get(1) - listFlushValue.get(0) == -1) {
                    straightFlushList.add(listFlushValue.get(0));
                }
            }
            straightFlushList.sort(Comparator.reverseOrder());
            List<Integer> resultStraightFlushList = new ArrayList<>();
            if (straightFlushList.size() >= 5) {
                for (int j = 0; j < 5; j++) {
                    resultStraightFlushList.add(straightFlushList.get(j));
                    if (j == 4) {
                        return resultStraightFlushList;
                    }
                }
            }
        }
        return List.of();
    }

    public List<Integer> checkQuads(List<Card> handPlusBoard) {
        HashMap<Integer, Integer> appreances = new HashMap<>();
        List<Integer> boardQuads = new ArrayList<>();
        int maxKickerQuads = 0;
        for (Card card : handPlusBoard) {
            appreances.put(card.getValue(), appreances.getOrDefault(card.getValue(), 0) + 1);
        }
        int maxApperances = Collections.max(appreances.values());
        if (maxApperances < 4) {
            return List.of();
        }
        for (Card card : handPlusBoard) {
            if (appreances.get(card.getValue()) == 4) {
                boardQuads.add(card.getValue());
            } else {
                maxKickerQuads = Math.max(maxKickerQuads, card.getValue());
            }
        }
        boardQuads.add(maxKickerQuads);
        return boardQuads;
    }

    public List<Integer> checkFull(List<Card> handPlusBoard) {
        HashMap<Integer, Integer> appearances = new HashMap<>();
        int maxTripsValue = 0;
        int maxPairValue = 0;
        for (Card card : handPlusBoard) {
            appearances.put(card.getValue(), appearances.getOrDefault(card.getValue(), 0) + 1);
        }
        for (Integer value : appearances.keySet()) {
            if (appearances.get(value) == 3) {
                maxTripsValue = Math.max(maxTripsValue, value);
            }
        }
        for (Integer value : appearances.keySet()) {
            if (appearances.get(value) == 2 || appearances.get(value) == 3) {
                if (value != maxTripsValue) {
                    maxPairValue = Math.max(maxPairValue, value);
                }
            }
        }

        if (maxTripsValue > 0 && maxPairValue > 0) {
            List<Integer> boardFull = new ArrayList<>();
            boardFull.add(maxTripsValue);
            boardFull.add(maxTripsValue);
            boardFull.add(maxTripsValue);
            boardFull.add(maxPairValue);
            boardFull.add(maxPairValue);
            return boardFull;
        }
        return List.of();
    }

    public List<Integer> checkFlush(List<Card> handPlusBoard) {
        HashMap<String, Integer> mapColor = new HashMap<>();
        ArrayList<Integer> listFlushValue = new ArrayList<>();
        for (Card card : handPlusBoard) {
            mapColor.put(card.getSuit(), mapColor.getOrDefault(card.getSuit(), 0) + 1);
        }
        int maxApperances = Collections.max(mapColor.values());
        if (maxApperances < 5) {
            return List.of();
        }
        for (Card card : handPlusBoard) {
            if (mapColor.get(card.getSuit()) == maxApperances) {
                listFlushValue.add(card.getValue());
            }
        }
        if (listFlushValue.contains(14)) {
            listFlushValue.add(1);
        }
        listFlushValue.sort(Collections.reverseOrder());
        return listFlushValue;
    }

    public List<Integer> checkStraight(List<Card> handPlusBoard) {
        List<Integer> straightList = new ArrayList<>();
        List<Integer> resultStraightList = new ArrayList<>();
        int streakStraight = 1;
        for (Card card : handPlusBoard) {
            straightList.add(card.getValue());
            if (card.getValue() == 14) {
                straightList.add(1);
            }
        }
        Collections.sort(straightList);
        for (int i = straightList.size() - 1; i > 0; i--) {
            if (straightList.get(i - 1).equals(straightList.get(i))) {
                continue;
            }
            if (straightList.get(i - 1) - straightList.get(i) == -1) {
                streakStraight++;
                resultStraightList.add(straightList.get(i));
            } else {
                streakStraight = 1;
                resultStraightList.clear();
            }
            if (streakStraight == 5) {
                resultStraightList.add(straightList.get(i - 1));
                return resultStraightList;
            }
        }
        resultStraightList.sort(Comparator.reverseOrder());
        return List.of();
    }

    public List<Integer> checkTrips(List<Card> handPlusBoard) {
        HashMap<Integer, Integer> appreances = new HashMap<>();
        List<Integer> boardTripsPlusKickers = new ArrayList<>();
        List<Integer> boardHighCards = new ArrayList<>();
        int maxTripsValue = 0;
        for (Card card : handPlusBoard) {
            appreances.put(card.getValue(), appreances.getOrDefault(card.getValue(), 0) + 1);
        }
        if (!appreances.containsValue(3)) {
            return List.of();
        }
        for (Card card : handPlusBoard) {
            if (appreances.get(card.getValue()) == 3) {
                maxTripsValue = Math.max(maxTripsValue, card.getValue());
            }
            if (appreances.get(card.getValue()) == 1) {
                boardHighCards.add(card.getValue());
            }
        }
        boardHighCards.sort(Collections.reverseOrder());
        boardTripsPlusKickers.add(maxTripsValue);
        boardTripsPlusKickers.add(maxTripsValue);
        boardTripsPlusKickers.add(maxTripsValue);
        if (!boardHighCards.isEmpty()) {
            boardTripsPlusKickers.add(boardHighCards.get(0));
        }
        if (boardHighCards.size() > 1) {
            boardTripsPlusKickers.add(boardHighCards.get(1));
        }

        return boardTripsPlusKickers;
    }

    public List<Integer> checkTwoPair(List<Card> handPlusBoard) {
        HashMap<Integer, Integer> appreances = new HashMap<>();
        int checkAmmountPair = 0;
        List<Integer> allPairs = new ArrayList<>();
        List<Integer> resultBoardTwoPairs = new ArrayList<>();
        int maxValueKickerTwoPair = 0;
        for (Card card : handPlusBoard) {
            appreances.put(card.getValue(), appreances.getOrDefault(card.getValue(), 0) + 1);
        }
        for (Card card : handPlusBoard) {
            if (appreances.get(card.getValue()) == 2) {
                allPairs.add(card.getValue());
                checkAmmountPair++;
            } else {
                maxValueKickerTwoPair = Math.max(maxValueKickerTwoPair, card.getValue());
            }
        }
        if (checkAmmountPair > 3) {
            Collections.sort(allPairs);
            resultBoardTwoPairs.add(allPairs.get(2));
            resultBoardTwoPairs.add(allPairs.get(2));
            resultBoardTwoPairs.add(allPairs.get(0));
            resultBoardTwoPairs.add(allPairs.get(0));
            resultBoardTwoPairs.add(maxValueKickerTwoPair);
            return resultBoardTwoPairs;
        }
        return List.of();
    }

    public List<Integer> checkPair(List<Card> handPlusBoard) {
        HashMap<Integer, Integer> appreances = new HashMap<>();
        List<Integer> kickersValue = new ArrayList<>();
        List<Integer> reusltBoardOnePair = new ArrayList<>();
        int checkPair = 0;
        int maxValuePair = 0;
        for (Card card : handPlusBoard) {
            appreances.put(card.getValue(), appreances.getOrDefault(card.getValue(), 0) + 1);
        }
        for (Card card : handPlusBoard) {
            if (appreances.get(card.getValue()) == 2) {
                maxValuePair = Math.max(maxValuePair, card.getValue());
                checkPair++;
            } else {
                kickersValue.add(card.getValue());
            }
        }
        if (checkPair > 0) {
            Collections.sort(kickersValue);
            reusltBoardOnePair.add(maxValuePair);
            reusltBoardOnePair.add(maxValuePair);
            kickersValue.sort(Collections.reverseOrder());
            reusltBoardOnePair.add(kickersValue.get(0));
            reusltBoardOnePair.add(kickersValue.get(1));
            reusltBoardOnePair.add(kickersValue.get(2));
            return reusltBoardOnePair;
        }
        return List.of();
    }

    public List<Integer> highCards(List<Card> handPlusBoard) {
        ArrayList<Integer> allCards = new ArrayList<>();
        for (Card card : handPlusBoard) {
            allCards.add(card.getValue());
        }
        allCards.sort(Comparator.reverseOrder());
        List<Integer> resultHighCards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            resultHighCards.add(allCards.get(i));
        }
        resultHighCards.sort(Comparator.reverseOrder());
        return resultHighCards;
    }


    public ResultHandOut winningHand(List<Card> hand, List<Card> board) {
        List<Card> hand1PlusBoard = new ArrayList<>();
        hand1PlusBoard.addAll(hand);
        hand1PlusBoard.addAll(board);
        int xd = 1;

        List<Integer> bestHandValues = checkStraightFlush(hand1PlusBoard);

        if (bestHandValues.size() >= 5) {
            return new ResultHandOut(9, bestHandValues);
        }
        bestHandValues = checkQuads(hand1PlusBoard);
        if (bestHandValues.size() >= 5) {
            return new ResultHandOut(8, bestHandValues);
        }

        bestHandValues = checkFull(hand1PlusBoard);
        if (bestHandValues.size() >= 5) {
            return new ResultHandOut(7, bestHandValues);
        }

        bestHandValues = checkFlush(hand1PlusBoard);
        if (bestHandValues.size() >= 5) {
            return new ResultHandOut(6, bestHandValues.subList(0, 5));
        }

        bestHandValues = checkStraight(hand1PlusBoard);
        if (bestHandValues.size() >= 5) {
            return new ResultHandOut(5, bestHandValues);
        }

        bestHandValues = checkTrips(hand1PlusBoard);
        if (bestHandValues.size() >= 5) {
            return new ResultHandOut(4, bestHandValues);
        }

        bestHandValues = checkTwoPair(hand1PlusBoard);
        if (bestHandValues.size() >= 5) {
            return new ResultHandOut(3, bestHandValues);
        }

        bestHandValues = checkPair(hand1PlusBoard);
        if (bestHandValues.size() >= 5) {
            return new ResultHandOut(2, bestHandValues);
        }
        bestHandValues = highCards(hand1PlusBoard);
        return new ResultHandOut(1, bestHandValues);
    }
}
