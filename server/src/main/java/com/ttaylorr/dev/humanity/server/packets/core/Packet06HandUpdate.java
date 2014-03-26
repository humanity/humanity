package com.ttaylorr.dev.humanity.server.packets.core;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.ttaylorr.dev.humanity.server.cards.card.WhiteCard;
import com.ttaylorr.dev.humanity.server.packets.Packet;

import java.util.Collection;
import java.util.List;

public class Packet06HandUpdate extends Packet {

    private final List<WhiteCard> newCards;
    private final List<WhiteCard> removedCards;

    public Packet06HandUpdate(Collection<WhiteCard> newCards, Collection<WhiteCard> removedCards) {
        this.newCards = Lists.newArrayList(newCards);
        this.removedCards = Lists.newArrayList(removedCards);
    }

    public ImmutableList<WhiteCard> getDrawnCards() {
        return ImmutableList.copyOf(newCards);
    }

    public ImmutableList<WhiteCard> getRemovedCards() {
        return ImmutableList.copyOf(removedCards);
    }

}
