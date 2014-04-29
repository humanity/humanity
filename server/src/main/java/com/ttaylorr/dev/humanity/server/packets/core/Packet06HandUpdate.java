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
    private final REMOVE_BEHAVIOR remove;

    public Packet06HandUpdate(Collection<WhiteCard> newCards, Collection<WhiteCard> removedCards) {
        this.newCards = Lists.newArrayList(newCards);
        this.removedCards = Lists.newArrayList(removedCards);
        remove = REMOVE_BEHAVIOR.UNSPECIFIED;
    }

    // perhaps a factory would be better here, but that can happen later. Should not have a dummy variable...but whatever!
    public Packet06HandUpdate(Collection<WhiteCard> newCards, REMOVE_BEHAVIOR behavior, int dummy) {
        this.newCards = Lists.newArrayList(newCards);
        removedCards = null;
        remove = behavior;
    }

    enum REMOVE_BEHAVIOR {REMOVE_ALL, REMOVE_NONE, UNSPECIFIED}

    public ImmutableList<WhiteCard> getDrawnCards() {
        return ImmutableList.copyOf(newCards);
    }

    public ImmutableList<WhiteCard> getRemovedCards() {
        return ImmutableList.copyOf(removedCards);
    }

    public boolean shouldRemoveAll() {
        return remove == REMOVE_BEHAVIOR.REMOVE_ALL;
    }
}
