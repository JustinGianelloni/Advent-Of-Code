package com.advent.day7;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Hand {

    public enum Card {
        JOKER (1),
        TWO (2),
        THREE (3),
        FOUR (4),
        FIVE (5),
        SIX (6),
        SEVEN (7),
        EIGHT (8),
        NINE (9),
        TEN (10),
        JACK (11),
        QUEEN (12),
        KING (13),
        ACE (14);

        private final int value;

        Card(int value) { this.value = value; }

        public int getValue() { return value; }
    }

    public enum HandType {
        FIVE_OF_A_KIND (7),
        FOUR_OF_A_KIND (6),
        FULL_HOUSE (5),
        THREE_OF_A_KIND (4),
        TWO_PAIR (3),
        ONE_PAIR (2),
        HIGH_CARD (1);

        private final int value;

        HandType(int value) { this.value = value; }

        public int getValue() { return value; }
    }

    protected final String description;
    protected Map<Character, Card> cardMap = HashMap.newHashMap(14);
    protected Card[] hand = new Card[5];
    protected final int bid;
    protected final HandType handType;
    protected HashMap<Card, Integer> cardCount = new HashMap<>();

    public Hand(String cards, int bid) {
        this.description = cards;
        this.bid = bid;
        buildCardMap();
        buildHand(cards.toCharArray());
        countCards();
        handType = determineHandType();
    }

    private void buildCardMap() {
        cardMap.put('2', Card.TWO);
        cardMap.put('3', Card.THREE);
        cardMap.put('4', Card.FOUR);
        cardMap.put('5', Card.FIVE);
        cardMap.put('6', Card.SIX);
        cardMap.put('7', Card.SEVEN);
        cardMap.put('8', Card.EIGHT);
        cardMap.put('9', Card.NINE);
        cardMap.put('T', Card.TEN);
        cardMap.put('J', Card.JACK);
        cardMap.put('Q', Card.QUEEN);
        cardMap.put('K', Card.KING);
        cardMap.put('A', Card.ACE);
        cardMap.put('X', Card.JOKER);
    }

    protected void countCards() {
        for (Card card : hand) {
            cardCount.merge(card, 1, Integer::sum);
        }
    }

    protected HandType determineHandType() {
        if (isFullHouse()) {
            return HandType.FULL_HOUSE;
        }
        if (isTwoPair()) {
            return HandType.TWO_PAIR;
        }
        return switch (getHighestMatchCount()) {
            case 5 -> HandType.FIVE_OF_A_KIND;
            case 4 -> HandType.FOUR_OF_A_KIND;
            case 3 -> HandType.THREE_OF_A_KIND;
            case 2 -> HandType.ONE_PAIR;
            default -> HandType.HIGH_CARD;
        };
    }

    protected int getHighestMatchCount() {
        return Collections.max(cardCount.values());
    }

    protected boolean isFullHouse() {
        return cardCount.containsValue(3) && cardCount.containsValue(2);
    }

    protected boolean isTwoPair() {
        int pairs = 0;
        for (Map.Entry<Card, Integer> set : cardCount.entrySet()) {
            if (set.getValue() == 2) {
                pairs++;
            }
        }
        if (pairs == 2) {
            return true;
        }
        return false;
    }

    protected void buildHand(char[] cards) {
        for (int i = 0; i < cards.length; i++) {
            hand[i] = cardMap.get(cards[i]);
        }
    }

    public int getBid() { return bid; }

    public int getPositionValue(int position) {
        return hand[position].getValue();
    }

    public HandType getHandType() { return handType; }

    @Override
    public String toString() { return description; }
}
