package com.GameBlackjack.blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private List<Card> hand;

    public Dealer() {
        this.hand = new ArrayList<>();
    }

    public List<Card> getHand() {
        return hand;
    }

    public void dealCard(Deck deck) {
       this.hand.add(deck.draw());
    }

    public int getHandValue(){

        int totalValue = 0;
        int numAces = 0;

        for (Card card : hand) {
            totalValue += card.getValue();
            if (card.getRank().equals("A")) {
                numAces++;
            }
        }
        while (totalValue > 21 && numAces > 0) {
            totalValue -= 10;
            numAces--;
        }
        return totalValue;
    }

    public void playTurn(Deck deck) {
        while (getHandValue() < 17) {
            dealCard(deck);
        }
    }

}
