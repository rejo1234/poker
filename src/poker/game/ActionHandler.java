package poker.game;

import poker.Card;
import poker.Deck;
import poker.EquityEvaluator;
import poker.GameResult;

import java.util.List;

public class ActionHandler {
    GameState gameState;
    public HandsAndBoard result;
    public Deck deck;
    public Player player1;
    public Player player2;
    public EquityEvaluator equityEvaluator;

    public ActionHandler(GameState gameState, HandsAndBoard result, Deck deck, Player player1, Player player2, EquityEvaluator equityEvaluator) {
        this.gameState = gameState;
        this.result = result;
        this.deck = deck;
        this.player1 = player1;
        this.player2 = player2;
        this.equityEvaluator = equityEvaluator;
    }

    public HandleActionResult handleAction(PossibleAction lastAction, Player activePlayer, Player nonActivePlayer) {
        HandleActionResult result = new HandleActionResult();
        if (lastAction == PossibleAction.LIMP) {
            hanldeLimp(activePlayer);
        } else if (lastAction == PossibleAction.RAISE) {
            handleRaisePlayer(activePlayer, nonActivePlayer);
        } else if (lastAction == PossibleAction.BET) {
            handleBetPlayer(activePlayer);
        } else if (lastAction == PossibleAction.CALL) {
            boolean isAllin = handleCall(activePlayer, nonActivePlayer);
            result.setAllIn(isAllin);
            if (isAllin) {
                handleCallAllIn(activePlayer, nonActivePlayer);
            }
        } else if (lastAction == PossibleAction.CHECK) {
            System.out.println(activePlayer.getNamePlayer() + " checks");
        } else {
            result.setFold(true);
            handleFold(activePlayer, nonActivePlayer);
        }
        return result;
    }

    public void handleCallAllIn(Player activePlayer, Player nonActivePlayer) {
        printEquity(gameState, activePlayer, nonActivePlayer, deck);
        gameState.setGamePhase(GamePhase.RIVER);
        handleNextStreet(gameState, activePlayer, nonActivePlayer);
    }

    public HandsAndBoard getHandsAndBoard() {
        HandsAndBoard handsAndBoard = deck.shuffleDeckAndGetHandsAndBoard();
        return handsAndBoard;
    }

    public void handleHandStart(Player activePlayer, Player nonActivePlayer) {
        this.result = getHandsAndBoard();
        this.gameState.setGamePhase(GamePhase.PREFLOP);
        player1.setHandPlayer(result.getHand1());
        player2.setHandPlayer(result.getHand2());
        if (activePlayer.getStackPlayer() < 100) {
            activePlayer.increaseStackPlyer(100 - activePlayer.getStackPlayer());
        }
        if (nonActivePlayer.getStackPlayer() < 100) {
            nonActivePlayer.increaseStackPlyer(100 - nonActivePlayer.getStackPlayer());
        }
        updatePotAndStackAmount(gameState.bigBlind, nonActivePlayer);
        updatePotAndStackAmount(gameState.smallBlind, activePlayer);
        activePlayer.increasePlayermoneyOnStreet(gameState.smallBlind);
        nonActivePlayer.increasePlayermoneyOnStreet(gameState.bigBlind);
        GamePlayUtils.printPreFlopDetails(nonActivePlayer, activePlayer, result, gameState);
    }

    public void handleNextHand() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
        player1.setEquity(0);
        player2.setEquity(0);
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public void printEquity(GameState gameState, Player player1, Player player2, Deck deck) {
        if (gameState.getGamePhase() == GamePhase.PREFLOP) {
            List<Double> equityPlayersPreFlop = equityEvaluator.calculateEquityPreFlop(result.getHand1(), result.getHand2(), deck);
            setAndPrintEquity(equityPlayersPreFlop, player1, player2);
        } else if (gameState.getGamePhase() == GamePhase.FLOP) {
            List<Card> firstThreeCards = result.getBoard().subList(0, 3);
            List<Double> equityPlayersFlop = equityEvaluator.calculateEquityFlop(firstThreeCards, result.getHand1(), result.getHand2(), deck);
            setAndPrintEquity(equityPlayersFlop, player1, player2);
        } else if (gameState.getGamePhase() == GamePhase.TURN) {
            List<Card> firstFourCards = result.getBoard().subList(0, 4);
            List<Double> equityPlayersTurn = equityEvaluator.calculateEquityTurn(firstFourCards, result.getHand1(), result.getHand2(), deck);
            setAndPrintEquity(equityPlayersTurn, player1, player2);
        }
    }

    public void setAndPrintEquity(List<Double> equityPlayers, Player player1, Player player2) {
        player1.setEquity(equityPlayers.get(0));
        player2.setEquity(equityPlayers.get(1));
        System.out.println(player1.getNamePlayer() + player1.getHandPlayer() + " " + equityPlayers.get(0));
        System.out.println(player2.getNamePlayer() + player2.getHandPlayer() + " " + equityPlayers.get(1));
    }

    public boolean handleCall(Player activePlayer, Player nonActivePlayer) {
        if (GamePlayUtils.amountToCall(nonActivePlayer, activePlayer) > activePlayer.getStackPlayer()) {
            return true;
        }
        updatePotAndStackAmount(GamePlayUtils.amountToCall(nonActivePlayer, activePlayer), activePlayer);
        return activePlayer.getStackPlayer() == 0 || nonActivePlayer.getStackPlayer() == 0;
    }


    public void handleNextStreet(GameState gameState, Player activePlayer, Player nonActivePlayer) {
        GamePlay.resetMaxValueRaises(activePlayer, nonActivePlayer);
        if (gameState.getGamePhase() == GamePhase.PREFLOP) {
            updateGameStateAndPrint(GamePhase.FLOP);
        } else if (gameState.getGamePhase() == GamePhase.FLOP) {
            updateGameStateAndPrint(GamePhase.TURN);
        } else if (gameState.getGamePhase() == GamePhase.TURN) {
            updateGameStateAndPrint(GamePhase.RIVER);
        } else {
            resultAtShowdown();
        }
    }

    public void updateGameStateAndPrint(GamePhase gamePhase) {
        GamePlayUtils.printPot(gameState);
        gameState.setGamePhase(gamePhase);
        GamePlayUtils.printBoard(gamePhase, result);
    }

    public void hanldeLimp(Player activePlayer) {
        System.out.println(activePlayer.getNamePlayer() + " limps 0,5$");
        updatePotAndStackAmount(gameState.smallBlind, activePlayer);
        activePlayer.increasePlayermoneyOnStreet(gameState.smallBlind);
    }

    public double scannerNextRaise() {
        return GamePlayUtils.scanner.nextInt();
    }

    public void handleRaisePlayer(Player activePlayer, Player nonActivePlayer) {
        System.out.println("Podaj do ilu, raise minimum " + Math.max(2, 2 * nonActivePlayer.getPreviousRaiseOrBet()));
        double raise = scannerNextRaise();
        if (raise >= activePlayer.stack) {
            raise = handleAllIn(activePlayer);
        } else {
            updatePotAndStackAmount(raise - activePlayer.getPlayerMoneyAtStrit(), activePlayer);
            System.out.println(activePlayer.getNamePlayer() + " raise to " + raise + "$");
            activePlayer.increasePlayermoneyOnStreet(raise - activePlayer.getPlayerMoneyAtStrit());
        }
        activePlayer.setPreviousRaiseOrBet(raise);
    }

    public double handleAllIn(Player activePlayer) {
        System.out.println(activePlayer.getNamePlayer() + " All in " +
                (activePlayer.getStackPlayer() + activePlayer.getPlayerMoneyAtStrit()) + "$");
        updatePotAndStackAmount(activePlayer.getStackPlayer(), activePlayer);
        return activePlayer.getStackPlayer();
    }

    public void handleFold(Player activePlayer, Player nonActivePlayer) {
        System.out.println(activePlayer.getNamePlayer() + " folds");
        System.out.println(nonActivePlayer.getNamePlayer() + " wins " + gameState.pot + "$");
        nonActivePlayer.increaseStackPlyer(gameState.getPot());
        nonActivePlayer.decreaseAmountInHand(nonActivePlayer.getAmountInHand());
        activePlayer.decreaseAmountInHand(activePlayer.getAmountInHand());
        gameState.decreasePot(gameState.pot);
    }

    public double scannerNextBet() {
        return GamePlayUtils.scanner.nextInt();
    }

    public void handleBetPlayer(Player activePlayer) {
        System.out.println("Podaj size beta");
        double bet = scannerNextBet();
        if (bet >= activePlayer.stack) {
            bet = handleAllIn(activePlayer);
            updatePotAndStackAmount(bet, player1);
        } else {
            System.out.println(activePlayer.getNamePlayer() + " bets " + bet + "$");
            updatePotAndStackAmount(bet, activePlayer);
        }
        activePlayer.increasePlayermoneyOnStreet(bet);
        activePlayer.setPreviousRaiseOrBet(bet);
    }

    public void resultAtShowdown() {
        boolean isDraw = false;
        GameResult.PlayerWinner whoWins = equityEvaluator.updatePlayerWins(result.getBoard(), result.getHand1(), result.getHand2());
        System.out.print(whoWins + " " + gameState.getPot() + "$");
        if (whoWins == GameResult.PlayerWinner.DRAW) {
            isDraw = true;
        }
        if (whoWins == GameResult.PlayerWinner.WIN_PLAYER1) {
            updatePlayersOnShowdownResult(player1, player2, isDraw);
        } else {
            updatePlayersOnShowdownResult(player2, player1, isDraw);
        }
    }

    public void updatePotAndStackAmount(double amount, Player player) {
        gameState.increasePot(amount);
        player.decreaseStackPlayer(amount);
        player.increaseAmountInHand(amount);
    }

    public void updatePlayersOnShowdownResult(Player player1, Player player2, Boolean isDraw) {
        player1.increaseStackPlyer(gameState.pot);
        player1.setAmountInHand(0);
        player2.setAmountInHand(0);
        if (isDraw) {
            player2.increaseStackPlyer(gameState.pot / 2);
        }
        gameState.setPot(0);
    }
}
