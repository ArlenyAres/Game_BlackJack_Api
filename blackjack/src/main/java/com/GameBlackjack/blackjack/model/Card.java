package com.GameBlackjack.blackjack.model;



import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Details about the card")
public class Card {

    @Schema(description = "The suit of the card", example = "Hearts")
    private String suit;

    @Schema(description = "The rank of the card", example = "Ace")
    private String rank;

    @Schema(description = "The value of the card", example = "11")
    private int value;


    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
