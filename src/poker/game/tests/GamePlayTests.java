package poker.game.tests;

import poker.Deck;
import poker.EquityEvaluator;
import poker.game.*;

public class GamePlayTests extends GamePlay {
    PossibleAction[] possibleAction;
    int index = 0;

    public GamePlayTests(ActionHandlerTests actionHandlerTests, GameState gameState, HandsAndBoard result, Deck deck, Player player1, Player player2, EquityEvaluator equityEvaluator) {
        super(actionHandlerTests, gameState, result, deck, player1, player2, equityEvaluator);
    }

    public void setIndexes(int index) {
        this.index = index;
    }

    public void setNextAction(PossibleAction[] action) {
        this.possibleAction = action;
    }

    @Override
    public PossibleAction getAction(PossibleAction lastAction, Player activePlayer, Player nonActivePlayer) {
        playerList.clear();
        index++;
        return possibleAction[index - 1];
    }
}
