package poker;

import java.util.List;
import java.util.Map;

public class Poker {
    public void init() {
        Deck myDeck = new Deck();
        List<Card> board = myDeck.getBoard("2c", "2d", "9c", "4c", "9d");
        List<Card> hand = myDeck.getHand("2h", "2s");
        List<Card> hand2 = myDeck.getHand("3d", "3s");
        String filePath = "equity_data.ser";
        Map<String, String> equityPlayers = EquityEvaluator.loadHashMapFromFile("equity_data.ser");
        EquityEvaluator myequityEvaluator = new EquityEvaluator(equityPlayers);
        myequityEvaluator.loadHashMapFromFile(filePath);
    }
}
