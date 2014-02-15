package com.ttaylorr.dev.humanity.server.cards.hand;

import com.ttaylorr.dev.humanity.server.cards.core.BlackCard;
import com.ttaylorr.dev.humanity.server.cards.core.WhiteCard;
import com.ttaylorr.dev.humanity.server.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Jack
 * Date: 2/10/14
 * Time: 9:33 AM
 */
public class CardController {
    private Deck<WhiteCard> whiteDeck;
    private Deck<BlackCard> blackDeck;
    private Map<Player, Hand> hands;

    private BlackCard currentBlackCard;
    private Map<Player, List<WhiteCard>> playedCards;

    CardController(Deck<WhiteCard> whiteDeck, Deck<BlackCard> blackDeck) {
        this.whiteDeck = whiteDeck;
        this.blackDeck = blackDeck;
        this.hands = new HashMap<>();
    }

    /**
     * Adds a Player (that doesn't already have a Hand) to hands.
     * If the player already has a Hand, then this operation isn't valid because the cards in the Hand are potentially untracked.
     *
     * This method is used to register players at the start of a game.
     *
     * @param player
     */
    public void addPlayer(Player player) {
        if (player == null || player.getHand() != null)
            throw new IllegalArgumentException("Player already has a Deck");
        Hand hand = getHandForPlayer(player);
        player.setHand(hand); // only intentional/acceptable use of this method.
        hands.put(player, hand);
    }

    private Hand getHandForPlayer(Player player) {
        Hand h = new Hand(player);
        h.autoDraw();
        return h;
    }

    public void setCurrentBlackCard(BlackCard blackCard) {
        currentBlackCard = blackCard;
    }

    public BlackCard getCurrentBlackCard() {
        return currentBlackCard;
    }

    public void playCards(Player player, List<WhiteCard> cards) {

    }

    public void playCard(Player player, WhiteCard card) {
    }

    /**
     * Reset the current setup to default/whatever.
     */
    public void resetBlackDeck(BlackCard newBlackCard) {
        currentBlackCard = newBlackCard;

        synchronized (whiteDeck) { // this operation must complete to ensure that there are as many cards as possible
            //  before the next round starts, thus depleting the Deck.
            for (List<WhiteCard> list : playedCards.values()) {
                for (WhiteCard c : list) {
                    whiteDeck.releaseCard(c);
                }
            }
        }

        playedCards.clear(); // remove all cards.
    }


}
