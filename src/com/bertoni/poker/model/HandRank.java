package com.bertoni.poker.model;

public enum HandRank {
    HIGH_CARD(1302540),
    ONE_PAIR(1098240),
    TWO_PAIR(123552),
    THREE_KIND(54912),
    STRAIGHT(10200),
    FLUSH(5108),
    FULL_HOUSE(3744),
    FOUR_KIND(624),
    STRAIGHT_FLUSH(36),
    ROYAL_FLUSH(4);
    
    public static long TOTAL_COMBINATIONS = 2598960;
    
    private long amount;
    
    private HandRank(long amount) {
        this.amount = amount;
    }
    
    public long getNumCombinations() {
        return amount;
    }
    
    public long getHigherRemainCombinations() {
        long sum = 0;
        for (HandRank rank : values()) {
            if (rank.amount < this.amount) {
                sum += rank.amount;
            }
        }
        return sum;
    }
}
