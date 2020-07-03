package com.bertoni.poker.model;

public enum Suit {
    DIAMONDS("D"), HEARTS("H"), SPADES("S"), CLUBS("C");
    
    private String symbol;

    private Suit(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
    
}
