package com.ttaylorr.dev.humanity.client.cards;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.client.HumanityClient;
import com.ttaylorr.dev.humanity.server.cards.hand.IHumanityHand;
import com.ttaylorr.dev.humanity.server.cards.card.WhiteCard;
import com.ttaylorr.dev.humanity.server.handlers.Handler;
import com.ttaylorr.dev.humanity.server.handlers.HandlerPriority;
import com.ttaylorr.dev.humanity.server.handlers.Listenable;
import com.ttaylorr.dev.humanity.server.packets.core.Packet06HandUpdate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientHumanityHand implements IHumanityHand, Listenable, Serializable {

    private final HumanityClient owner;
    private List<WhiteCard> cards;

    public ClientHumanityHand(HumanityClient owner) {
        this(owner, new ArrayList<WhiteCard>(IHumanityHand.MAX_HAND_SIZE));
    }

    public ClientHumanityHand(HumanityClient owner, ArrayList<WhiteCard> cards) {
        this.cards = new ArrayList<>();
        this.owner = Preconditions.checkNotNull(owner, "owner");
        this.owner.getPacketHandler().registerHandlers(this);
    }

    @Handler(priority = HandlerPriority.MONITOR) // TODO it seems to me that this would be a high-priority, not a run-last item.
    public void onHandUpdate(Packet06HandUpdate packet) {
        this.cards = packet.getCards();
        System.out.println(this.getCards());
    }

    @Override
    public boolean releaseCard(WhiteCard card) {
        return this.cards.remove(card);
    }

    @Override
    public boolean shouldDraw() {
        return this.cards.size() != IHumanityHand.MAX_HAND_SIZE;
    }

    @Override
    public List<WhiteCard> getCards() {
        return this.cards;
    }
}
