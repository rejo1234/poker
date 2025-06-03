package poker.game;

public class GameState {
    public GamePhase gamePhase;
    double pot;
    public double smallBlind = 0.5;
    public double bigBlind = 1;

    public GameState(GamePhase gamePhase, double pot) {
        this.gamePhase = gamePhase;
        this.pot = pot;

    }

    public GamePhase getGamePhase() {
        return gamePhase;
    }

    public void setGamePhase(GamePhase gamePhase) {
        this.gamePhase = gamePhase;
    }

    public double getPot() {
        return pot;
    }

    public double setPot(double amount) {
        return this.pot = amount;
    }

    public double decreasePot(double amount) {
        this.pot = pot - amount;
        return pot;
    }

    public double increasePot(double amount) {
        this.pot = pot + amount;
        return this.pot;
    }
}
