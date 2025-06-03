package poker;

import java.util.List;

public class ResultHandOut {
    int value;
    List<Integer> bestHandValues;

    public ResultHandOut(int value, List<Integer> bestHandValues) {
        this.value = value;
        this.bestHandValues = bestHandValues;
    }

    public List<Integer> getBestHandValues() {
        return bestHandValues;
    }

    public int getValue() {
        return value;
    }
}
