package com.ttaylorr.dev.humanity.server.player;

import com.ttaylorr.dev.humanity.server.cards.core.Card;
import com.ttaylorr.dev.humanity.server.cards.hand.Hand;

import java.io.Serializable;
import java.util.List;

public class Player implements Serializable {

    private String name;
    private boolean isCardCsar;
    private Hand hand;
    private int points;

    public Player(String name) {
        this.name = name;
        hand = new Hand(this);
    }

    /**
     * Submit the cards that the player would like to play this round.
     *
     * @param cards the card(s) the player wants to submit. This method
     *              generates the CardSelectionPacket that will be sent to the server.
     */
    void submitCards(List<Card> cards) {
    }

    public boolean isCardCsar() {
        return isCardCsar;
    }

    public void setCardCsar(boolean isCardCsar) {
        this.isCardCsar = isCardCsar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Hand getHand() {
        return hand;
    }

    /**
     *
     * @param hand
     */
    @Deprecated // to discourage use--this may be used only in CardController, but Java doesn't have friend classes.
    public void setHand(Hand hand) {
        this.hand = hand;
    }

}
