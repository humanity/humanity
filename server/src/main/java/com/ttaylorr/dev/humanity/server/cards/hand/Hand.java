package com.ttaylorr.dev.humanity.server.cards.hand;

import com.ttaylorr.dev.humanity.server.cards.core.WhiteCard;
import com.ttaylorr.dev.humanity.server.player.Player;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Hand implements Serializable {
    List<WhiteCard> cards; // package-protected for access to CardController
    static int cardsPerHand;
    private Player owner;

    static {
        cardsPerHand = 7; // TODO replace with a call to the Configuration object.
    }

    public Hand(Player player) {
        cards = new LinkedList<WhiteCard>();
        this.owner = player;
    }

    /**
     * Automatically draw all the cards that are needed based on the current number of cards in the player's hand.
     */
   void autoDraw() {
        for (; cards.size() <= cardsPerHand; ) {
            cards.add(Deck.getWhiteDeck().drawCard());
        }
    }

    public boolean isAccesible() {
        return !this.owner.isCardCsar();
    }

    /**
     * Allow clients of this class to read the cards, but never change them.
     * @return An iterator to an unmodifiable version of the List that represents the cards.
     */
    public Iterator<WhiteCard> getIterator() {
        return Collections.unmodifiableList(cards).iterator();
    }
}
