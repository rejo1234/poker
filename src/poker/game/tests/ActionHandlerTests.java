package poker.game.tests;

import poker.Deck;
import poker.EquityEvaluator;
import poker.game.ActionHandler;
import poker.game.GameState;
import poker.game.HandsAndBoard;
import poker.game.Player;

import java.util.ArrayList;
import java.util.List;

public class ActionHandlerTests extends ActionHandler {
    public static int indexRaises = 0;
    public static int indexBets = 0;
    public static int indexAllScenariosBets = 0;
    public static int indexPossibleHandsAndBoard = 0;

    public HandsAndBoard getHandsAndBoard() {
        List<HandsAndBoard> allPossiblHandsAndBoard = GameTests.shuffleDeckAndGetHandsAndBoard();
        if (indexPossibleHandsAndBoard == allPossiblHandsAndBoard.size()) {
            indexPossibleHandsAndBoard = 0;
        }
        return allPossiblHandsAndBoard.get(indexPossibleHandsAndBoard++);
    }

    public ActionHandlerTests(GameState gameState, HandsAndBoard result, Deck deck, Player player1, Player player2, EquityEvaluator equityEvaluator) {
        super(gameState, result, deck, player1, player2, equityEvaluator);
    }

    @Override
    public double scannerNextBet() {
        ArrayList<ArrayList<Double>> allPossibleScenatiosBets = GameTests.possibleBets();
        ArrayList<Double> oneHandBets = allPossibleScenatiosBets.get(indexAllScenariosBets);
        if (indexBets == oneHandBets.size() - 1) {
            indexAllScenariosBets++;
        }
        return oneHandBets.get(indexBets++);
    }

    @Override
    public double scannerNextRaise() {
        ArrayList<Double> possibleRaises = GameTests.possibleRaises();
        return possibleRaises.get(indexRaises++);
    }
}
