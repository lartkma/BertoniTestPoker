package com.bertoni.poker.model;

public enum CardValue {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("T", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13),
    ACE("A", 14);
    
    private String symbol;
    private int value;
    
    private CardValue(String symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getValue() {
        return value;
    }
    
    public static CardValue getBySymbol(String symbol) {
        for (CardValue value : values()) {
            if (value.getSymbol().equals(symbol)) {
                return value;
            }
        }
        throw new IllegalArgumentException("No symbol " + symbol);
    }
}
