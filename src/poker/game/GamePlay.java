package poker.game;

import poker.Deck;
import poker.EquityEvaluator;
import poker.GameResult;

import java.util.ArrayList;
import java.util.List;

public class GamePlay {
    public Deck deck;
    public HandsAndBoard result;
    public Player player1;
    public Player player2;
    public GameState gameState;
    public List<Player> playerList;
    public EquityEvaluator equityEvaluator;
    public GameResult gameResult;
    public ActionHandler actionHandler;


    public GamePlay(ActionHandler actionHandler, GameState gameState, HandsAndBoard result, Deck deck, Player player1, Player player2, EquityEvaluator equityEvaluator) {
        this.gameState = gameState;
        this.result = result;
        this.deck = deck;
        this.player1 = player1;
        this.player2 = player2;
        this.equityEvaluator = equityEvaluator;
        playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        this.gameResult = new GameResult();
        this.actionHandler = actionHandler;
    }

    public void gameStart() {
        Player smallBlindPlayer = player2;
        Player bigBlindPlayer = player1;
        boolean outOfPostionPlayer = false;
        PossibleAction lastAction = null;
        HandleActionResult handleActionResult = new HandleActionResult();
        while (playerList.size() > 1) {
            Player nonActivePlayer = bigBlindPlayer;
            Player activePlayer = smallBlindPlayer;
            actionHandler.handleHandStart(activePlayer, nonActivePlayer);
            for (int i = 0; i < 4; i++) {
                while (nextTurnPlayer(lastAction, outOfPostionPlayer)) {
                    outOfPostionPlayer = activePlayer != smallBlindPlayer && gameState.gamePhase != GamePhase.PREFLOP;
                    lastAction = getAction(lastAction, activePlayer, nonActivePlayer);
                    handleActionResult = actionHandler.handleAction(lastAction, activePlayer, nonActivePlayer);
                    Player temp = activePlayer;
                    activePlayer = nonActivePlayer;
                    nonActivePlayer = temp;
                }
                lastAction = null;
                activePlayer = bigBlindPlayer;
                nonActivePlayer = smallBlindPlayer;
                if (handleActionResult.isFold || handleActionResult.isAllIn) {
                    break;
                }
                actionHandler.handleNextStreet(gameState, activePlayer, nonActivePlayer);
            }
            Player temp = smallBlindPlayer;
            smallBlindPlayer = bigBlindPlayer;
            bigBlindPlayer = temp;
            actionHandler.handleNextHand();
        }
    }

    public boolean nextTurnPlayer(PossibleAction lastAction, Boolean OutOfPositionPlayer) {
        if (lastAction == null) {
            return true;
        } else if (lastAction == PossibleAction.BET || lastAction == PossibleAction.RAISE ||
                lastAction == PossibleAction.LIMP || (lastAction == PossibleAction.CHECK && OutOfPositionPlayer)) {
            return true;
        } else {
            return false;
        }
    }

    public PossibleAction getAction(PossibleAction lastAction, Player activePlayer, Player nonActivePlayer) {
        GamePlayUtils.printInfoBeforeStreet(activePlayer, nonActivePlayer);
        int decision;
        if (lastAction == null && gameState.getGamePhase() == GamePhase.PREFLOP) {
            return getActionFor("1 limp 2 raise", PossibleAction.LIMP, PossibleAction.RAISE);
        } else if (lastAction == null && gameState.getGamePhase() != GamePhase.PREFLOP) {
            return getActionFor("1 check 2 bet ", PossibleAction.CHECK, PossibleAction.BET);
        } else if (lastAction == PossibleAction.CHECK) {
            return getActionFor("1 check 2 bet ", PossibleAction.CHECK, PossibleAction.BET);
        } else if (lastAction == PossibleAction.BET || lastAction == PossibleAction.RAISE) {
            System.out.println(GamePlayUtils.amountToCall(nonActivePlayer, activePlayer) + "$ to call");
            if (GamePlayUtils.amountToCall(nonActivePlayer, activePlayer) >= activePlayer.getStackPlayer() + activePlayer.getAmountInHand()) {
                System.out.println("1 Call, 3 Fold");
            } else if (activePlayer.getStackPlayer() + activePlayer.getAmountInHand() == nonActivePlayer.getAmountInHand()) {
                System.out.println("1 Call, 3 Fold");
            } else if (nonActivePlayer.getAmountInHand() > activePlayer.getStackPlayer() + activePlayer.getAmountInHand()) {
                System.out.println("1 Call, 3 Fold");
            } else {
                System.out.println("1 Call, 2 Raise, 3 Fold");
            }
            decision = GamePlayUtils.scanner.nextInt();
            if (decision == 1) {
                if (GamePlayUtils.amountToCall(nonActivePlayer, activePlayer) >= activePlayer.getStackPlayer()) {
                    System.out.println(activePlayer.getNamePlayer() + " All In $" + (activePlayer.getPlayerMoneyAtStrit() + activePlayer.getStackPlayer()));
                } else {
                    System.out.println(activePlayer.getNamePlayer() + " calls $" + GamePlayUtils.amountToCall(nonActivePlayer, activePlayer));
                }
                return PossibleAction.CALL;
            } else if (decision == 2) {
                return PossibleAction.RAISE;
            } else {
                return PossibleAction.FOLD;
            }
        } else {
            return getActionFor("1 check 2 raise", PossibleAction.CHECK, PossibleAction.RAISE);
        }
    }

    public PossibleAction getActionFor(String stringDecisions, PossibleAction possibleAction, PossibleAction possibleAction2) {
        System.out.println(stringDecisions);
        int decision = GamePlayUtils.scanner.nextInt();
        if (decision == 1) {
            return possibleAction;
        }
        return possibleAction2;
    }

    public static void resetMaxValueRaises(Player activePlayer, Player nonActivePlayer) {
        activePlayer.setPreviousRaiseOrBet(0);
        nonActivePlayer.setPreviousRaiseOrBet(0);
        activePlayer.setPlayerMoneyOnStreet(0);
        nonActivePlayer.setPlayerMoneyOnStreet(0);
    }
}
