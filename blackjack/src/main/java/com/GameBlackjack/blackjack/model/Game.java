package com.GameBlackjack.blackjack.model;


import com.GameBlackjack.blackjack.exception.ResourceNotFoundException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "games")
public class Game {

    @Id
    private String id;
    private List<Player> players;
    private Deck deck;
    private Dealer dealer;
    private boolean isActive;
    private int currentPlayerIndex;

    public void startGame(List<Player> players) {
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

    public List<Player>determineWinners(){
        int dealerHandValue = getHandValue(dealer.getHand());

        // Filtrar jugadores que no han excedido 21
        List<Player> activePlayers = players.stream()
                .filter(player -> getHandValue(player.getHands()) <= 21)
                .collect(Collectors.toList());

        // Determinar ganadores
        List<Player> winners = activePlayers.stream()
                .filter(player -> {
                    int playerHandValue = getHandValue(player.getHands());
                    return (playerHandValue > dealerHandValue || dealerHandValue > 21) && playerHandValue <= 21;
                })
                .collect(Collectors.toList());

        // Actualizar puntuacion de los ganadores
        winners.forEach(winner -> winner.setScore(winner.getScore() + winner.getBet()));

        return winners;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }
}
