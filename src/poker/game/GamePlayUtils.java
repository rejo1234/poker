package poker.game;

import java.util.Map;
import java.util.Scanner;

public class GamePlayUtils {
    public static final Scanner scanner = new Scanner(System.in);
    public static final Map<GamePhase, Integer> BOARD_PRINT_INDEXES = Map.of(
            GamePhase.FLOP, 3,
            GamePhase.TURN, 4,
            GamePhase.RIVER, 5
    );

    public static void printPot(GameState gameState) {
        System.out.println("Pot wynosi " + gameState.getPot() + "$");
    }

    public static void printBoard(GamePhase gamePhase, HandsAndBoard result) {
        System.out.println(gamePhase);
        int endIndex = BOARD_PRINT_INDEXES.get(gamePhase);
        for (int i = 0; i < endIndex; i++) {
            System.out.print(result.getBoard().get(i) + " ");
        }
        System.out.println();
    }

    public static double amountToCall(Player player1, Player player2) {
        if (player1.getAmountInHand() - player2.getAmountInHand() > player2.getAmountInHand() + player2.getStackPlayer()) {
            return player2.getStackPlayer();
        }
        return player1.getAmountInHand() - player2.getAmountInHand();
    }

    public static void printPreFlopDetails(Player playerBigBlind, Player playerSmallBlind, HandsAndBoard result, GameState gameState) {
        System.out.println(result.getBoard());
        System.out.println(playerBigBlind.getNamePlayer());
        System.out.println(playerBigBlind.getHandPlayer());
        System.out.println(playerBigBlind.getStackPlayer());
        System.out.println(playerSmallBlind.getNamePlayer());
        System.out.println(playerSmallBlind.getHandPlayer());
        System.out.println(playerSmallBlind.getStackPlayer());
        System.out.println("Zaczyna " + playerSmallBlind.getNamePlayer());
        System.out.println(playerBigBlind.getNamePlayer() + " bigBlind " + gameState.bigBlind + "$");
        System.out.println(playerSmallBlind.getNamePlayer() + " smallBlind " + gameState.smallBlind + "$");
        GamePlayUtils.printPot(gameState);
        System.out.println(playerSmallBlind.getNamePlayer() + " podejmuje decyzje");
        System.out.println(playerSmallBlind.getNamePlayer() + " " + GamePlayUtils.amountToCall(playerBigBlind, playerSmallBlind) + "$ to call");
    }

    public static void printInfoBeforeStreet(Player activePlayer, Player nonActivePlayer) {
        System.out.println(activePlayer.getStackPlayer() + " " + activePlayer.getNamePlayer());
        System.out.println(nonActivePlayer.getStackPlayer() + " " + nonActivePlayer.getNamePlayer());
        System.out.println(activePlayer.getNamePlayer() + " decyduje");
        System.out.println("Podaj decyzje");
    }

}
