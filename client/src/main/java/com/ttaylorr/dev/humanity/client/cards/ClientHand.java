package com.ttaylorr.dev.humanity.client.cards;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.client.HumanityClient;
import com.ttaylorr.dev.humanity.server.cards.IHumanityHand;
import com.ttaylorr.dev.humanity.server.cards.WhiteCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientHand implements IHumanityHand, Serializable {

    private final HumanityClient owner;
    private final List<WhiteCard> cards;

    public ClientHand(HumanityClient owner) {
        this.cards = new ArrayList<>();
        this.owner = Preconditions.checkNotNull(owner, "owner");
    }

    @Override
    public boolean releaseCard(WhiteCard card) {
        return this.cards.remove(card);
    }

    @Override
    public boolean addCard(WhiteCard card) {
        return this.cards.add(card);
    }

    @Override
    public boolean shouldDraw() {
        return this.cards.size() != IHumanityHand.MAX_HAND_SIZE;
    }
}
