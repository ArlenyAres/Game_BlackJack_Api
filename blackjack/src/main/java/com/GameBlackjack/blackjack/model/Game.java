package com.GameBlackjack.blackjack.model;


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

}
