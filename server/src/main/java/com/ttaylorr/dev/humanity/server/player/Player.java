package com.ttaylorr.dev.humanity.server.player;

import com.ttaylorr.dev.humanity.server.cards.core.Card;
import com.ttaylorr.dev.humanity.server.cards.hand.Hand;
import com.ttaylorr.dev.humanity.server.packets.Packet;

import java.io.Serializable;
import java.util.List;

public class Player implements Serializable{

    private String name;
    private boolean isCardCsar;
    private final Hand hand;

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
}
