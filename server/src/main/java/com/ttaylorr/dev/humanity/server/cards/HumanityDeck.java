package com.ttaylorr.dev.humanity.server.cards;

import java.util.Iterator;
import java.util.Set;

public abstract class HumanityDeck<T extends HumanityCard> {

    protected final Set<T> cards;

    public HumanityDeck(Set<T> cards) {
        this.cards = cards;
    }

    public final Set<T> getCards() {
        return this.cards;
    }

    public final synchronized T pickFirst() {
        for(Iterator<T> it = this.cards.iterator(); it.hasNext(); ) {
            T card = it.next();
            if(card.isPlayable()) {
                return card;
            }
        }
        return null;
    }
}
