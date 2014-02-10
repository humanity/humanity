package com.ttaylorr.dev.humanity.server.cards.hand;

import com.ttaylorr.dev.humanity.server.cards.core.WhiteCard;
import com.ttaylorr.dev.humanity.server.player.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Jack
 * Date: 2/10/14
 * Time: 9:33 AM
 */
public class CardController {
    private Deck<WhiteCard> deck;
    private Map<Player, Hand> hands;

    CardController(Deck<WhiteCard> deck) {
        this.deck = deck;
        this.hands = new HashMap<>();
    }

    /**
     * Adds a Player (that doesn't already have a Hand) to hands.
     * If the player already has a Hand, then this operation isn't valid because the cards in the Hand are potentially untracked.
     *
     * @param player
     */
    public void addPlayer(Player player) {
        if (player == null || player.getHand() != null)
            throw new IllegalArgumentException("Player already has a Deck");
        Hand hand = getHandForPlayer(player);
        player.setHand(hand);
        hands.put(player, hand);
    }

    private Hand getHandForPlayer(Player player) {
        Hand h = new Hand(player);
        h.autoDraw();
        return h;
    }

}
