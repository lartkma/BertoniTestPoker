package com.bertoni.poker.model;

public class Card {

    private Suit suit;
    private CardValue value;

    public Card(Suit suit, CardValue value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getNumericalValue() {
        return value.getValue();
    }

    public static Card getBySymbol(String symbol) {
        return new Card(Suit.getBySymbol(symbol.substring(1, 2)),
                CardValue.getBySymbol(symbol.substring(0, 1)));
    }
}
