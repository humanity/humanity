package com.ttaylorr.dev.humanity.server.player;

import com.ttaylorr.dev.humanity.server.cards.hand.Hand;

import java.io.Serializable;

public class Player implements Serializable {

    private String name;
    private boolean isCardCsar;
    private Hand hand;
    private int points;

    public Player(String name) {
        this.name = name;
        hand = new Hand(this);
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
     * @param hand
     */
    @Deprecated // to discourage use--this may be used only in CardController, but Java doesn't have friend classes.
    public void setHand(Hand hand) {
        this.hand = hand;
    }

}
