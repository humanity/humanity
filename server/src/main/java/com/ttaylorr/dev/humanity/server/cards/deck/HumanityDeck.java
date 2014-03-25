package com.ttaylorr.dev.humanity.server.cards.deck;

import com.ttaylorr.dev.humanity.server.cards.card.state.CardState;
import com.ttaylorr.dev.humanity.server.cards.card.HumanityCard;

import java.util.*;

/**
 * TODO at some point, a Configuration-based constructor can be added so that cards can be added from files.
 * @param <T>
 */
public abstract class HumanityDeck<T extends HumanityCard> {

    protected final Set<T> cards;

    public HumanityDeck(Set<T> cards) {
        this.cards = cards;
    }

    public final Set<T> getCards() {
        return this.cards;
    }

    public final synchronized T drawCard() {
        for(Iterator<T> it = this.cards.iterator(); it.hasNext(); ) {
            T card = it.next();
            if(card.isPlayable()) {
                it.remove();
                card.setState(CardState.IN_HAND);
                return card;
            }
        }
        return null; // no cards can be played at all, so this user must...?
    }

    @Override
    public String toString() {
        return this.cards.toString();
    }
}
