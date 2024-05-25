package com.advent.day7;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WildHand extends Hand {

    public WildHand(String cards, int bid) {
        super(cards, bid);
    }

    @Override
    protected void countCards() {
        for (Hand.Card card : hand) {
            cardCount.merge(card, 1, Integer::sum);
        }
        if (Arrays.asList(hand).contains(Card.JOKER) &&
                cardCount.get(Card.JOKER) != 5) {
            HashMap<Card, Integer> jokerFreeCount = new HashMap<>();
            for (Map.Entry<Hand.Card, Integer> set : cardCount.entrySet()) {
                if (set.getKey() != Card.JOKER) {
                    jokerFreeCount.put(set.getKey(), set.getValue());
                }
            }
            for (Map.Entry<Hand.Card, Integer> set : cardCount.entrySet()) {
                if (set.getKey() == Card.JOKER) {
                    jokerFreeCount.merge(Collections.max(jokerFreeCount.entrySet(), Map.Entry.comparingByValue()).getKey(), cardCount.get(Card.JOKER), Integer::sum);
                }
            }
            cardCount = jokerFreeCount;
        }
    }
}
