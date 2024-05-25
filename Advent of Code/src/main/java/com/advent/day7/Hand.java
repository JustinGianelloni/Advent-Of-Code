package com.advent.day7;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Hand {

    public enum Card {
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
        ACE ( 14);

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

    public static final Map<Character, Card> cardMap;
    static {
        Map<Character, Card> map = HashMap.newHashMap(13);
        map.put('2', Card.TWO);
        map.put('3', Card.THREE);
        map.put('4', Card.FOUR);
        map.put('5', Card.FIVE);
        map.put('6', Card.SIX);
        map.put('7', Card.SEVEN);
        map.put('8', Card.EIGHT);
        map.put('9', Card.NINE);
        map.put('T', Card.TEN);
        map.put('J', Card.JACK);
        map.put('Q', Card.QUEEN);
        map.put('K', Card.KING);
        map.put('A', Card.ACE);
        cardMap = Collections.unmodifiableMap(map);
    }

    private final Card[] hand = new Card[5];
    private final int bid;
    private final HandType handType;
    private final HashMap<Card, Integer> cardCount = new HashMap<>();

    public Hand(char[] cards, int bid) {
        this.bid = bid;
        buildHand(cards);
        countCards();
        handType = determineHandType();
    }

    private void countCards() {
        for (Card card : hand) {
            cardCount.merge(card, 1, Integer::sum);
        }
    }

    private HandType determineHandType() {
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

    private int getHighestMatchCount() {
        return Collections.max(cardCount.values());
    }

    private boolean isFullHouse() {
        return cardCount.containsValue(3) && cardCount.containsValue(2);
    }

    private boolean isTwoPair() {
        int check = 0;
        for (Card card : hand) {
            if (card.getValue() == 2) {
                check++;
            }
        }
        if (check == 2) {
            return true;
        }
        return false;
    }

    private void buildHand(char[] cards) {
        for (int i = 0; i < cards.length; i++) {
            hand[i] = cardMap.get(cards[i]);
        }
    }

    public int getBid() { return bid; }

    public int getPositionValue(int position) {
        return hand[position].getValue();
    }

    public HandType getHandType() { return handType; }
}
