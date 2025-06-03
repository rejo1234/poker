package poker;

public class GameResult {
    int player1Wins;
    int player2Wins;
    int ties;

    public GameResult() {
        this.player1Wins = 0;
        this.player2Wins = 0;
        this.ties = 0;
    }

    public int getPlayer1Wins() {
        return player1Wins;
    }

    public int getPlayer2Wins() {
        return player2Wins;
    }

    public int getTies() {
        return ties;
    }

    public void increasePlayer1Wins(int wynik) {
        this.player1Wins += wynik;
    }

    public void increasePlayer2Wins(int wynik) {
        this.player2Wins += wynik;
    }

    public void increaseTies(int wynik) {
        this.ties += wynik;
    }

    public enum PlayerWinner {
        WIN_PLAYER1,
        WIN_PLAYER2,
        DRAW
    }
}
