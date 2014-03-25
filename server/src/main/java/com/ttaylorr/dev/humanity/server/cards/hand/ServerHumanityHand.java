package com.ttaylorr.dev.humanity.server.cards.hand;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.ttaylorr.dev.humanity.server.cards.card.WhiteCard;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServerHumanityHand implements IHumanityHand, Serializable {

    private final ClientConnection owner;
    private final List<WhiteCard> cards;

    public ServerHumanityHand(ClientConnection owner) {
        this.cards = new ArrayList<>();
        this.owner = Preconditions.checkNotNull(owner, "owner");
    }

    @Override
    public boolean releaseCard(WhiteCard card) {
        return this.cards.remove(card);
    }

    public boolean addCard(WhiteCard card) {
        return this.cards.add(card);
    }

    @Override
    public boolean shouldDraw() {
        return this.cards.size() != IHumanityHand.MAX_HAND_SIZE;
    }

    @Override
    public ImmutableList<WhiteCard> getCards() {
        return ImmutableList.copyOf(this.cards);
    }
}
