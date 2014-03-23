package com.ttaylorr.dev.humanity.server.cards;

import java.util.Set;

public class WhiteCardDeck extends HumanityDeck<WhiteCard> {

    private WhiteCard currentCard;

    public WhiteCardDeck(Set<WhiteCard> cards) {
        super(cards);
        currentCard = null;
    }

    public WhiteCard getCurrentCard() {
        return currentCard;
    }


}
