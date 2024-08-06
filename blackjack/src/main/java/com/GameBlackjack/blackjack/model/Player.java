package com.GameBlackjack.blackjack.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Schema(description = "Details about the player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique ID of the player", example = "1")
    private long id;

    @Schema(description = "The name of the player", example = "John Doe")
    private String name;

    @Schema(description = "The score of the player", example = "100")
    private int score;

    @Schema(description = "The bet of the player", example = "50")
    private int bet;

    @Schema(description = "The playing status of the player", example = "true")
    private boolean isPlaying;

    @Transient
    @Schema(description = "The cards in the player's hand")
    private List<Card> hands;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public List<Card> getHands() {
        return hands;
    }

    public void setHands(List<Card> hands) {
        this.hands = hands;
    }
}
