package poker.game;

import poker.Card;

import java.util.List;

public class Player {
    public double stack;
    public double amountInHand;
    public double previousRaise;
    public double moneyOnStreet;
    List<Card> handPlayer;
    String name;
    public double equity;

    public Player(double equity, double stack, double amountInHand, double previousRaise, double moneyOnStreet, List<Card> handPlayer, String name) {
        this.equity = equity;
        this.stack = stack;
        this.handPlayer = handPlayer;
        this.amountInHand = amountInHand;
        this.previousRaise = previousRaise;
        this.moneyOnStreet = moneyOnStreet;
        this.name = name;
    }

    public void setStack(double amount) {
        this.stack = amount;
    }

    public void setHandPlayer(List<Card> newHand) {
        this.handPlayer = newHand;
    }

    public String getNamePlayer() {
        return name;
    }

    public double setEquity(double amount) {
        return this.equity = amount;
    }

    public double getEquity() {
        return equity;
    }

    public List<Card> getHandPlayer() {
        return handPlayer;
    }

    public double getStackPlayer() {
        return stack;
    }

    public double getPlayerMoneyAtStrit() {
        return moneyOnStreet;
    }

    public void increasePlayermoneyOnStreet(double amount) {
        this.moneyOnStreet = moneyOnStreet + amount;
    }

    public void setPlayerMoneyOnStreet(double amount) {
        this.moneyOnStreet = amount;
    }

    public double getAmountInHand() {
        return amountInHand;
    }

    public void setPreviousRaiseOrBet(double amount) {
        this.previousRaise = amount;
    }

    public double getPreviousRaiseOrBet() {
        return previousRaise;
    }

    public double increaseStackPlyer(double amount) {
        this.stack = stack + amount;
        return stack;
    }

    public double setAmountInHand(double amount) {
        return this.amountInHand = amount;
    }

    public double decreaseAmountInHand(double amount) {
        this.amountInHand = amountInHand - amount;
        return amountInHand;
    }

    public double increaseAmountInHand(double amount) {
        this.amountInHand = amountInHand + amount;
        return amountInHand;
    }

    public double decreaseStackPlayer(double amount) {
        this.stack = this.stack - amount;
        return stack;
    }
}
