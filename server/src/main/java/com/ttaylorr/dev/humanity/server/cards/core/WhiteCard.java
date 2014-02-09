package com.ttaylorr.dev.humanity.server.cards.core;

public class WhiteCard extends Card {

    public WhiteCard(String nText) {
        super(nText);
    }

    @Override
    public boolean equals(Card c) {
        return c instanceof WhiteCard && c.toString().equals(this.toString());
    }

}
