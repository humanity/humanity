package com.ttaylorr.dev.humanity.server.cards.core;

import com.ttaylorr.dev.humanity.server.player.Player;

import java.io.Serializable;

public abstract class Card implements Serializable {
    private final String text;
    private Player owner;

    Card(String nText) {
        text = nText;
    }

    public abstract boolean equals(Card c);

    public String toString() {
        return text;
    }

}
