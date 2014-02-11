package com.ttaylorr.dev.humanity.server.cards.hand;

import com.ttaylorr.dev.humanity.server.cards.core.WhiteCard;
import com.ttaylorr.dev.humanity.server.player.Player;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Hand implements Serializable {
    List<WhiteCard> cards; // package-protected
    static int cardsPerHand;
    private Player owner;

    static {
        cardsPerHand = 7;
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

    public Iterator<WhiteCard> getIterator() {
        return cards.iterator();
    }

}
