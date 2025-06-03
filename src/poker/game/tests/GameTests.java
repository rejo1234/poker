package poker.game.tests;

import poker.Card;
import poker.Deck;
import poker.EquityEvaluator;
import poker.game.*;

import java.util.ArrayList;
import java.util.List;

public class GameTests {
    GamePlayTests myGamePlayTests;

    public GameTests(EquityEvaluator equityEvaluator, Player player2, Player player1, HandsAndBoard result, Deck deck, ActionHandlerTests actionHandlerTests, GameState gameState) {
        myGamePlayTests = new GamePlayTests(actionHandlerTests, gameState, result, deck, player1, player2, equityEvaluator);
    }

    public void startTests() {
        PossibleAction[][] allPossileActions = getTestScenarios();
        List<List<Double>> allPossibleStacks = possibleStacks();
        List<List<Double>> allPossibeEquities = possibleEquities();
        for (int i = 0; i < allPossileActions.length; i++) {
            System.out.println(i);
            PossibleAction[] possibleActions = allPossileActions[i];
            List<Double> currentStacks = allPossibleStacks.get(i);
            List<Double> currentEquities = allPossibeEquities.get(i);
            myGamePlayTests.setNextAction(possibleActions);
            myGamePlayTests.gameStart();
            validate(myGamePlayTests.player1.stack, myGamePlayTests.player2.stack, currentStacks, currentEquities, myGamePlayTests.player1.getEquity(), myGamePlayTests.player2.getEquity());
            prepareNextTest();
        }
    }

    public static List<HandsAndBoard> shuffleDeckAndGetHandsAndBoard() {
        List<Card> hand1 = new ArrayList<>();
        hand1.add(new Card(2, "s"));
        hand1.add(new Card(2, "h"));

        List<Card> hand2 = new ArrayList<>();
        hand2.add(new Card(3, "s"));
        hand2.add(new Card(3, "h"));
        List<Card> board = new ArrayList<>();
        board.add(new Card(2, "s"));
        board.add(new Card(3, "d"));
        board.add(new Card(4, "c"));
        board.add(new Card(7, "c"));
        board.add(new Card(8, "h"));

        List<Card> hand3 = new ArrayList<>();
        hand3.add(new Card(14, "s"));
        hand3.add(new Card(13, "h"));

        List<Card> hand4 = new ArrayList<>();
        hand4.add(new Card(11, "s"));
        hand4.add(new Card(11, "h"));

        List<Card> board2 = new ArrayList<>();
        board2.add(new Card(2, "d"));
        board2.add(new Card(5, "d"));
        board2.add(new Card(12, "c"));
        board2.add(new Card(2, "c"));
        board2.add(new Card(9, "h"));
        List<HandsAndBoard> allHandsAndBoard = new ArrayList<>();
        allHandsAndBoard.add(new HandsAndBoard(hand1, hand2, board));
        allHandsAndBoard.add(new HandsAndBoard(hand3, hand4, board2));
        return allHandsAndBoard;
    }

    public static ArrayList<ArrayList<Double>> possibleBets() {
        ArrayList<ArrayList<Double>> allPossibleBetsScenarios = new ArrayList<>();
        ArrayList<Double> possibleBets = new ArrayList<>();
        possibleBets.add(5.0);
        ArrayList<Double> possibleBets2 = new ArrayList<>();
        possibleBets2.add(5.0);
        ArrayList<Double> possibleBets3 = new ArrayList<>();
        possibleBets3.add(5.0);
        ArrayList<Double> possibleBets4 = new ArrayList<>();
        possibleBets4.add(5.0);
        ArrayList<Double> possibleBets5 = new ArrayList<>();
        possibleBets5.add(5.0);
        ArrayList<Double> possibleBets6 = new ArrayList<>();
        possibleBets6.add(5.0);
        possibleBets6.add(10.0);
        ArrayList<Double> possibleBets7 = new ArrayList<>();
        possibleBets7.add(5.0);
        possibleBets7.add(10.0);
        possibleBets7.add(20.0);
        ArrayList<Double> possibleBets8 = new ArrayList<>();
        possibleBets8.add(100.0);
        ArrayList<Double> possibleBets9 = new ArrayList<>();
        possibleBets9.add(5.0);
        possibleBets9.add(105.0);
        ArrayList<Double> possibleBets10 = new ArrayList<>();
        possibleBets10.add(5.0);
        possibleBets10.add(20.0);
        possibleBets10.add(105.0);
        allPossibleBetsScenarios.add(possibleBets);
        allPossibleBetsScenarios.add(possibleBets2);
        allPossibleBetsScenarios.add(possibleBets3);
        allPossibleBetsScenarios.add(possibleBets4);
        allPossibleBetsScenarios.add(possibleBets5);
        allPossibleBetsScenarios.add(possibleBets6);
        allPossibleBetsScenarios.add(possibleBets7);
        allPossibleBetsScenarios.add(possibleBets8);
        allPossibleBetsScenarios.add(possibleBets9);
        allPossibleBetsScenarios.add(possibleBets10);
        return allPossibleBetsScenarios;
    }

    public static ArrayList<Double> possibleRaises() {
        ArrayList<Double> possibleRaises = new ArrayList<>();
        possibleRaises.add(5.0);
        possibleRaises.add(10.0);
        possibleRaises.add(20.0);
        possibleRaises.add(40.0);
        possibleRaises.add(80.0);
        return possibleRaises;
    }

    public void prepareNextTest() {
        ActionHandlerTests.indexBets = 0;
        ActionHandlerTests.indexRaises = 0;
        myGamePlayTests.player1.setEquity(0);
        myGamePlayTests.player2.setEquity(0);
        myGamePlayTests.playerList.add(myGamePlayTests.player1);
        myGamePlayTests.playerList.add(myGamePlayTests.player2);
        myGamePlayTests.player1.setStack(100);
        myGamePlayTests.player2.setStack(100);
        myGamePlayTests.setIndexes(0);
        myGamePlayTests.player1.setPlayerMoneyOnStreet(0);
        myGamePlayTests.player2.setPlayerMoneyOnStreet(0);
    }

    private void validate(double stack, double stack2, List<Double> stacks, List<Double> equities, double equity, double equity2) {
        if (myGamePlayTests.gameState.gamePhase == GamePhase.PREFLOP) {
            if (stack == stacks.get(0) && stack2 == stacks.get(1) && equity == equities.get(0) && equity2 == equities.get(1)) {
                System.out.println("dobrze sprawdza");
            } else {
                throw new Error("expected " + stacks + " otrzymałem " + stack + " " + stack2 +
                        " expected " + equities + " otrzymałem " + equity + " " + equity2);
            }
        } else {
            if (stack == stacks.get(1) && stack2 == stacks.get(0) && equity == equities.get(1) && equity2 == equities.get(0)) {
                System.out.println("dobrze sprawdza");
            } else {
                throw new Error("expected " + stacks + " otrzymałem " + stack2 + " " + stack +
                        " expected " + equities + " otrzymałem " + equity2 + " " + equity);
            }
        }
    }

    public static List<List<Double>> possibleEquities() {
        return List.of(
                List.of(0.0, 0.0),
                List.of(0.0, 0.0),
                List.of(0.0, 0.0),
                List.of(0.0, 0.0),
                List.of(0.0, 0.0),
                List.of(0.0, 0.0),
                List.of(0.0, 0.0),
                List.of(0.0, 0.0),
                List.of(0.0, 0.0),
                List.of(0.0, 0.0),
                List.of(0.0, 0.0),
                List.of(0.0, 0.0),
                List.of(0.0, 0.0),
                List.of(0.0, 0.0),
                List.of(0.0, 0.0),
                List.of(0.0, 0.0),
                List.of(0.1, 0.9),
                List.of(0.86, 0.14),
                List.of(0.0, 0.0)
        );
    }

    public static PossibleAction[][] getTestScenarios() {
        return new PossibleAction[][]{
                {PossibleAction.LIMP, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.FOLD},
                {PossibleAction.LIMP, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.FOLD},
                {PossibleAction.LIMP, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.FOLD},
                {PossibleAction.LIMP, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.FOLD},
                {PossibleAction.LIMP, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.FOLD},
                {PossibleAction.LIMP, PossibleAction.CHECK, PossibleAction.FOLD},
                {PossibleAction.LIMP, PossibleAction.FOLD},
                {PossibleAction.FOLD},
                {PossibleAction.LIMP, PossibleAction.CHECK, PossibleAction.BET, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.FOLD},
                {PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.BET, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.FOLD},
                {PossibleAction.RAISE, PossibleAction.RAISE, PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.FOLD},
                {PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.BET, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK},
                {PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.BET, PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK},
                {PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.BET, PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK},
                {PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.BET, PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.BET, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.CHECK},
                {PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.BET, PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.BET, PossibleAction.CALL, PossibleAction.BET, PossibleAction.CALL},
                {PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.BET, PossibleAction.CALL, PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.BET, PossibleAction.CALL, PossibleAction.BET, PossibleAction.CALL},
                {PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.BET, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.BET, PossibleAction.CALL, PossibleAction.BET, PossibleAction.CALL},
                {PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.BET, PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.BET, PossibleAction.CALL, PossibleAction.BET, PossibleAction.CALL}
        };
    }

    public static List<List<Double>> possibleStacks() {
        return List.of(
                List.of(99.0, 101.0),
                List.of(101.0, 99.0),
                List.of(99.0, 101.0),
                List.of(101.0, 99.0),
                List.of(99.0, 101.0),
                List.of(101.0, 99.0),
                List.of(99.0, 101.0),
                List.of(100.5, 99.5),
                List.of(94.0, 106.0),
                List.of(90.0, 110.0),
                List.of(120.0, 80.0),
                List.of(110.0, 90.0),
                List.of(115.0, 85.0),
                List.of(115.0, 85.0),
                List.of(125.0, 75.0),
                List.of(145.0, 55.0),
                List.of(200.0, 0.0),
                List.of(200.0, 0.0),
                List.of(200.0, 0.0)
        );
    }
}
