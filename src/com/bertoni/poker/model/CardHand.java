package com.bertoni.poker.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

public class CardHand implements Comparable<CardHand> {
    
    private static final int CARDS_IN_HAND = 5;
    
    private List<Card> cards;
    private HandRank rank;
    private int rankValue;
    
    public CardHand(Card card1, Card card2, Card card3, Card card4, Card card5) {
        this.cards = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5));
        evaluateHand(this.cards);
    }
    
    private void evaluateHand(List<Card> cards) {
        boolean consecutive = false;

        cards.sort((c1, c2) -> c2.getNumericalValue() - c1.getNumericalValue()); // Highest to lowest
        if (cards.get(0).getNumericalValue() - cards.get(1).getNumericalValue() == 1 &&
                cards.get(1).getNumericalValue() - cards.get(2).getNumericalValue() == 1 &&
                cards.get(2).getNumericalValue() - cards.get(3).getNumericalValue() == 1 &&
                cards.get(3).getNumericalValue() - cards.get(4).getNumericalValue() == 1) {
            consecutive = true;
            this.rank = HandRank.STRAIGHT;
            this.rankValue = cards.get(0).getNumericalValue();
        }
        
        if (cards.stream().allMatch(c -> c.getSuit() == cards.get(0).getSuit())) {
            if (consecutive) {
                if (this.rankValue == CardValue.ACE.getValue()) {
                    this.rank = HandRank.ROYAL_FLUSH;
                } else {
                    this.rank = HandRank.STRAIGHT_FLUSH;
                }
            } else {
                this.rank = HandRank.FLUSH;
                this.rankValue = cards.get(0).getNumericalValue();
            }
        }
        
        // If a royal flush or straight flush has been found, stop, there is not any higher rank
        if (this.rank != HandRank.ROYAL_FLUSH && this.rank != HandRank.STRAIGHT_FLUSH) {
            LinkedHashMap<Integer, Integer> counts = new LinkedHashMap<>();
            cards.forEach(c -> {
                counts.putIfAbsent(c.getNumericalValue(), 0);
                counts.put(c.getNumericalValue(), counts.get(c.getNumericalValue()) + 1);
            });
            
            int fourValue = -1, threeValue = -1, pairValue = -1, secondPairValue = -1;
            for (Entry<Integer, Integer> entry : counts.entrySet()) {
                int value = entry.getKey();
                int count = entry.getValue();
                
                if (count >= 4) {
                    fourValue = value;
                } else if (count == 3) {
                    threeValue = value;
                } else if (count == 2){
                    if (pairValue == -1) {
                        pairValue = value;
                    } else {
                        secondPairValue = value;
                    }
                }
            }
            
            if (fourValue != -1) {
                this.rank = HandRank.FOUR_KIND;
                this.rankValue = fourValue;
            } else if (threeValue != -1) {
                if (pairValue != -1) {
                    this.rank = HandRank.FULL_HOUSE;
                    this.rankValue = threeValue;
                } else if (this.rank != HandRank.FLUSH && this.rank != HandRank.STRAIGHT) {
                    // Those possibilities were calculated before and are higher than 3-of-a-kind
                    this.rank = HandRank.THREE_KIND;
                    this.rankValue = threeValue;
                }
            } else if (pairValue != -1 && this.rank != HandRank.FLUSH && this.rank != HandRank.STRAIGHT) {
                if (secondPairValue != -1) {
                    this.rank = HandRank.TWO_PAIR;
                    this.rankValue = (pairValue > secondPairValue ? pairValue : secondPairValue);
                } else {
                    this.rank = HandRank.ONE_PAIR;
                    this.rankValue = pairValue;
                }
            }
            
            // If none found
            if (this.rank == null) {
                this.rank = HandRank.HIGH_CARD;
                this.rankValue = cards.get(0).getNumericalValue();
            }
        }
    }
    
    public HandRank getRank() {
        return rank;
    }
    
    public int getRankValue() {
        return rankValue;
    }

    @Override
    public int compareTo(CardHand other) {
        int compareRanks = this.rank.compareTo(other.rank);
        
        if (compareRanks != 0) {
            return compareRanks > 0 ? 1 : -1;
        } else {
            int compareValues = this.rankValue - other.rankValue;
            if (compareValues != 0) {
                return compareValues > 0 ? 1 : -1;
            } else {
                // We know lists are sorted
                for (int i = 0; i < CARDS_IN_HAND; i++) {
                    int compareMax = this.cards.get(i).getNumericalValue() - other.cards.get(i).getNumericalValue();
                    if (compareMax != 0) {
                        return compareMax > 0 ? 1 : -1;
                    }
                }
            }
        }

        // If no difference found
        return 0;
    }

}
