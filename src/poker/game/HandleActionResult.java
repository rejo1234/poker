package poker.game;

public class HandleActionResult {
    boolean isAllIn;
    boolean isFold;

    public HandleActionResult() {

    }

    public void setAllIn(boolean isAllIn) {
        this.isAllIn = isAllIn;
    }

    public void setFold(boolean isFold) {
        this.isFold = isFold;
    }
}
