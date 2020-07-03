package com.bertoni.poker.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CardHandTest {

    @Test
    public void testRoyalFlush() {
        CardHand toTest = new CardHand(new Card(Suit.DIAMONDS, CardValue.QUEEN),
                new Card(Suit.DIAMONDS, CardValue.KING),
                new Card(Suit.DIAMONDS, CardValue.ACE),
                new Card(Suit.DIAMONDS, CardValue.JACK),
                new Card(Suit.DIAMONDS, CardValue.TEN));
        assertEquals(HandRank.ROYAL_FLUSH, toTest.getRank());
        assertEquals(CardValue.ACE.getValue(), toTest.getRankValue());
    }
    
    @Test
    public void testStaightFlush() {
        CardHand toTest = new CardHand(new Card(Suit.DIAMONDS, CardValue.NINE),
                new Card(Suit.DIAMONDS, CardValue.EIGHT),
                new Card(Suit.DIAMONDS, CardValue.FIVE),
                new Card(Suit.DIAMONDS, CardValue.SEVEN),
                new Card(Suit.DIAMONDS, CardValue.SIX));
        assertEquals(HandRank.STRAIGHT_FLUSH, toTest.getRank());
        assertEquals(CardValue.NINE.getValue(), toTest.getRankValue());
    }
    
    @Test
    public void testFourOfAKind() {
        CardHand toTest = new CardHand(new Card(Suit.DIAMONDS, CardValue.FIVE),
                new Card(Suit.CLUBS, CardValue.FIVE),
                new Card(Suit.HEARTS, CardValue.FIVE),
                new Card(Suit.SPADES, CardValue.FIVE),
                new Card(Suit.DIAMONDS, CardValue.NINE));
        assertEquals(HandRank.FOUR_KIND, toTest.getRank());
        assertEquals(CardValue.FIVE.getValue(), toTest.getRankValue());
    }
    
    @Test
    public void testFullHouse() {
        CardHand toTest = new CardHand(new Card(Suit.DIAMONDS, CardValue.FIVE),
                new Card(Suit.CLUBS, CardValue.FIVE),
                new Card(Suit.HEARTS, CardValue.FIVE),
                new Card(Suit.SPADES, CardValue.NINE),
                new Card(Suit.DIAMONDS, CardValue.NINE));
        assertEquals(HandRank.FULL_HOUSE, toTest.getRank());
        assertEquals(CardValue.FIVE.getValue(), toTest.getRankValue());
    }
    
    @Test
    public void testFlush() {
        CardHand toTest = new CardHand(new Card(Suit.DIAMONDS, CardValue.NINE),
                new Card(Suit.DIAMONDS, CardValue.EIGHT),
                new Card(Suit.DIAMONDS, CardValue.FIVE),
                new Card(Suit.DIAMONDS, CardValue.SEVEN),
                new Card(Suit.DIAMONDS, CardValue.THREE));
        assertEquals(HandRank.FLUSH, toTest.getRank());
        assertEquals(CardValue.NINE.getValue(), toTest.getRankValue());
    }
    
    @Test
    public void testStaight() {
        CardHand toTest = new CardHand(new Card(Suit.DIAMONDS, CardValue.NINE),
                new Card(Suit.SPADES, CardValue.EIGHT),
                new Card(Suit.DIAMONDS, CardValue.FIVE),
                new Card(Suit.SPADES, CardValue.SEVEN),
                new Card(Suit.HEARTS, CardValue.SIX));
        assertEquals(HandRank.STRAIGHT, toTest.getRank());
        assertEquals(CardValue.NINE.getValue(), toTest.getRankValue());
    }
    
    @Test
    public void testRoyalStraight() {
        CardHand toTest = new CardHand(new Card(Suit.DIAMONDS, CardValue.QUEEN),
                new Card(Suit.SPADES, CardValue.KING),
                new Card(Suit.DIAMONDS, CardValue.ACE),
                new Card(Suit.HEARTS, CardValue.JACK),
                new Card(Suit.DIAMONDS, CardValue.TEN));
        assertEquals(HandRank.STRAIGHT, toTest.getRank());
        assertEquals(CardValue.ACE.getValue(), toTest.getRankValue());
    }
    
    @Test
    public void testThreeKind() {
        CardHand toTest = new CardHand(new Card(Suit.DIAMONDS, CardValue.JACK),
                new Card(Suit.SPADES, CardValue.KING),
                new Card(Suit.DIAMONDS, CardValue.ACE),
                new Card(Suit.HEARTS, CardValue.JACK),
                new Card(Suit.DIAMONDS, CardValue.JACK));
        assertEquals(HandRank.THREE_KIND, toTest.getRank());
        assertEquals(CardValue.JACK.getValue(), toTest.getRankValue());
    }
    
    @Test
    public void testTwoPairs() {
        CardHand toTest = new CardHand(new Card(Suit.DIAMONDS, CardValue.JACK),
                new Card(Suit.SPADES, CardValue.TEN),
                new Card(Suit.DIAMONDS, CardValue.ACE),
                new Card(Suit.HEARTS, CardValue.TEN),
                new Card(Suit.DIAMONDS, CardValue.JACK));
        assertEquals(HandRank.TWO_PAIR, toTest.getRank());
        assertEquals(CardValue.JACK.getValue(), toTest.getRankValue());
    }
    
    @Test
    public void testOnePair() {
        CardHand toTest = new CardHand(new Card(Suit.DIAMONDS, CardValue.JACK),
                new Card(Suit.SPADES, CardValue.TEN),
                new Card(Suit.DIAMONDS, CardValue.ACE),
                new Card(Suit.HEARTS, CardValue.SEVEN),
                new Card(Suit.DIAMONDS, CardValue.SEVEN));
        assertEquals(HandRank.ONE_PAIR, toTest.getRank());
        assertEquals(CardValue.SEVEN.getValue(), toTest.getRankValue());
    }
    
    @Test
    public void testOneCard() {
        CardHand toTest = new CardHand(new Card(Suit.DIAMONDS, CardValue.JACK),
                new Card(Suit.SPADES, CardValue.TEN),
                new Card(Suit.DIAMONDS, CardValue.ACE),
                new Card(Suit.HEARTS, CardValue.SEVEN),
                new Card(Suit.DIAMONDS, CardValue.SIX));
        assertEquals(HandRank.HIGH_CARD, toTest.getRank());
        assertEquals(CardValue.ACE.getValue(), toTest.getRankValue());
    }
    
    @Test
    public void testCompareHandsSameRankDifferentValue() {
        CardHand hand1 = new CardHand(new Card(Suit.HEARTS, CardValue.FIVE),
                new Card(Suit.CLUBS, CardValue.FIVE),
                new Card(Suit.SPADES, CardValue.SIX),
                new Card(Suit.SPADES, CardValue.SEVEN),
                new Card(Suit.DIAMONDS, CardValue.KING));
        
        CardHand hand2 = new CardHand(new Card(Suit.CLUBS, CardValue.TWO),
                new Card(Suit.SPADES, CardValue.THREE),
                new Card(Suit.SPADES, CardValue.EIGHT),
                new Card(Suit.DIAMONDS, CardValue.EIGHT),
                new Card(Suit.DIAMONDS, CardValue.TEN));
        
        assertEquals(-1, hand1.compareTo(hand2));
    }
    
    @Test
    public void testCompareHandsHighestCardDifferentValue() {
        CardHand hand1 = new CardHand(new Card(Suit.DIAMONDS, CardValue.FIVE),
                new Card(Suit.CLUBS, CardValue.EIGHT),
                new Card(Suit.SPADES, CardValue.NINE),
                new Card(Suit.SPADES, CardValue.JACK),
                new Card(Suit.CLUBS, CardValue.ACE));
        
        CardHand hand2 = new CardHand(new Card(Suit.CLUBS, CardValue.TWO),
                new Card(Suit.CLUBS, CardValue.FIVE),
                new Card(Suit.DIAMONDS, CardValue.SEVEN),
                new Card(Suit.SPADES, CardValue.EIGHT),
                new Card(Suit.HEARTS, CardValue.QUEEN));
        
        assertEquals(1, hand1.compareTo(hand2));
    }
    
    @Test
    public void testCompareHandsDifferentRank() {
        CardHand hand1 = new CardHand(new Card(Suit.DIAMONDS, CardValue.TWO),
                new Card(Suit.CLUBS, CardValue.NINE),
                new Card(Suit.SPADES, CardValue.ACE),
                new Card(Suit.HEARTS, CardValue.ACE),
                new Card(Suit.CLUBS, CardValue.ACE));
        
        CardHand hand2 = new CardHand(new Card(Suit.DIAMONDS, CardValue.THREE),
                new Card(Suit.DIAMONDS, CardValue.SIX),
                new Card(Suit.DIAMONDS, CardValue.SEVEN),
                new Card(Suit.DIAMONDS, CardValue.TEN),
                new Card(Suit.DIAMONDS, CardValue.QUEEN));
        
        assertEquals(-1, hand1.compareTo(hand2));
    }
    
    @Test
    public void testCompareHandsSameRankAndValue() {
        CardHand hand1 = new CardHand(new Card(Suit.DIAMONDS, CardValue.FOUR),
                new Card(Suit.SPADES, CardValue.SIX),
                new Card(Suit.HEARTS, CardValue.NINE),
                new Card(Suit.HEARTS, CardValue.QUEEN),
                new Card(Suit.CLUBS, CardValue.QUEEN));
        
        CardHand hand2 = new CardHand(new Card(Suit.DIAMONDS, CardValue.THREE),
                new Card(Suit.DIAMONDS, CardValue.SIX),
                new Card(Suit.HEARTS, CardValue.SEVEN),
                new Card(Suit.DIAMONDS, CardValue.QUEEN),
                new Card(Suit.SPADES, CardValue.QUEEN));
        
        assertEquals(1, hand1.compareTo(hand2));
    }
    
    @Test
    public void testCompareHandsFullHouse() {
        CardHand hand1 = new CardHand(new Card(Suit.HEARTS, CardValue.TWO),
                new Card(Suit.DIAMONDS, CardValue.TWO),
                new Card(Suit.CLUBS, CardValue.FOUR),
                new Card(Suit.DIAMONDS, CardValue.FOUR),
                new Card(Suit.SPADES, CardValue.FOUR));
        
        CardHand hand2 = new CardHand(new Card(Suit.CLUBS, CardValue.THREE),
                new Card(Suit.DIAMONDS, CardValue.THREE),
                new Card(Suit.SPADES, CardValue.THREE),
                new Card(Suit.SPADES, CardValue.NINE),
                new Card(Suit.DIAMONDS, CardValue.NINE));
        
        assertEquals(1, hand1.compareTo(hand2));
    }
}
