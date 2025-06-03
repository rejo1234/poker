import poker.Poker;
import poker.game.StartGamePlay;


public class Main {
    public static void main(String[] args) {
      //  startPoker();
        startGame();
    }
    public static void startGame() {
        StartGamePlay myStartGamePlay = new StartGamePlay();
        myStartGamePlay.initGame();
    }
    public static void startPoker() {
        Poker myPoker = new Poker();
        myPoker.init();
    }
}