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

    public static Suit getBySymbol(String symbol) {
        for (Suit value : values()) {
            if (value.getSymbol().equals(symbol)) {
                return value;
            }
        }
        throw new IllegalArgumentException("No symbol " + symbol);
    }

}
