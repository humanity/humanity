package com.ttaylorr.dev.humanity.server.packets.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.ttaylorr.dev.humanity.server.cards.card.WhiteCard;
import com.ttaylorr.dev.humanity.server.packets.Packet;

import java.util.List;

public class Packet06HandUpdate extends Packet {

    private ImmutableList<WhiteCard> cards;

    public Packet06HandUpdate(List<WhiteCard> cards) {
        this.cards = ImmutableList.copyOf(Preconditions.checkNotNull(cards, "cards"));
    }

    public ImmutableList<WhiteCard> getCards() {
        return this.cards;
    }
}
