package poker.pokerTest;

public class HandEquity {
    private double possibleHandsCount;
    private double bestHandCount;


    public HandEquity() {
        possibleHandsCount = bestHandCount = 0;
    }

    public void playersGotTie(boolean isBestHand) {
        possibleHandsCount++;
        if (isBestHand) {
            bestHandCount = bestHandCount + 0.5;
        }
    }

    public void addPossibleHand(boolean isBestHand) {
        possibleHandsCount++;

        if (isBestHand) {
            bestHandCount++;
        }
    }


    public int getEquity() {
        if (possibleHandsCount == 0)
            return 0;

        return (int) Math.round(bestHandCount * 100.0 / possibleHandsCount);
    }


    @Override
    public String toString() {
        int equity = this.getEquity();
        return String.format("%d %%", equity);
    }


    public String toStringDetails() {
        return String.format("%s (%d/%d)", this.toString(), bestHandCount, possibleHandsCount);
    }
}
