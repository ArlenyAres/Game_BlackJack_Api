package com.GameBlackjack.blackjack.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

public class Deck {

    private Stack<Card> cards;

    public Deck() {
        cards = new Stack<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                cards.push(new Card(suit, ranks[i], values[i]));
            }
        }
    }

    public Card draw() {
        return cards.pop();
    }

//    public void shuffle() {
//        Stack<Card> temp = new Stack<>();
//        while (!cards.isEmpty()) {
//            int loc = (int) (Math.random() * cards.size());
//            for (int i = 0; i < loc; i++) {
//                temp.push(cards.pop());
//            }
//            Card c = cards.pop();
//            while (!temp.isEmpty()) {
//                cards.push(temp.pop());
//            }
//            temp.push(c);
//        }
//        while (!temp.isEmpty()) {
//            cards.push(temp.pop());
//        }
//    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public void setCards(Stack<Card> cards) {
        this.cards = cards;
    }
}
