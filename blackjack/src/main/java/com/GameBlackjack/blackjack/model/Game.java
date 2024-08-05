package com.GameBlackjack.blackjack.model;


import com.GameBlackjack.blackjack.exception.ResourceNotFoundException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "games")
public class Game {

    @Id
    private String id;
    private List<Player> players;
    private Deck deck;
    private Dealer dealer;
    private boolean isActive;
    private int currentPlayerIndex;

    public void starGame(List<Player> players) {
        this.players = players;
        this.deck = new Deck();
       // this.deck.initializeDeck();
        this.deck.shuffle();
        this.dealer = new Dealer();
        this.dealer.dealCard(deck);
        this.isActive = true;
        this.currentPlayerIndex = 0;

        for (Player player : players) {
            player.setHands(List.of(deck.draw(), deck.draw()));
            player.setPlaying(true);
        }

    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }


    public void nextTurn() {

        currentPlayerIndex += 1;
        if (currentPlayerIndex >= players.size()) {
            isActive = false;
            dealer.playTurn(deck);
        }
    }

    public void playerHit(){

        Player currentPlayer = getCurrentPlayer();
        currentPlayer.getHands().add(deck.draw());

        if(getHandValue(currentPlayer.getHands()) > 21){
            currentPlayer.setPlaying(false);
        }
    }

//    public void playerStand(){
//        Player currentPlayer = getCurrentPlayer();
//        currentPlayer.setPlaying(false);
//    }

    public void playerStand(){
        getCurrentPlayer().setPlaying(false);
        nextTurn();
    }

    public int getHandValue(List<Card> hand) {
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

    public void placeBet(Long playerId, int amount) {

        Player player = players.stream()
                .filter(p -> p.getId() == playerId)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + playerId));

    }



}
