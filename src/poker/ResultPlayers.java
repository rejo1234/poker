package poker;

public class ResultPlayers {
    private ResultHandOut resultPlayer1;
    private ResultHandOut resultPlayer2;

    public ResultPlayers(ResultHandOut resultPlayer1, ResultHandOut resultPlayer2) {
        this.resultPlayer1 = resultPlayer1;
        this.resultPlayer2 = resultPlayer2;
    }

    public ResultHandOut getResultPlayer1() {
        return resultPlayer1;
    }

    public ResultHandOut getResultPlayer2() {
        return resultPlayer2;
    }
}
