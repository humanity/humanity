package com.ttaylorr.dev.humanity.client.definition;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.client.HumanityClient;
import com.ttaylorr.dev.humanity.client.cards.ClientHand;
import com.ttaylorr.dev.humanity.server.cards.hand.IHumanityHand;
import com.ttaylorr.dev.humanity.server.client.definition.IClientDefinition;
import com.ttaylorr.dev.humanity.server.client.player.PlayerState;
import com.ttaylorr.dev.humanity.server.handlers.Handler;
import com.ttaylorr.dev.humanity.server.handlers.HandlerPriority;
import com.ttaylorr.dev.humanity.server.handlers.Listenable;
import com.ttaylorr.dev.humanity.server.packets.core.Packet05PlayerStateChange;

public class ClientClientDefinition implements IClientDefinition, Listenable {

    private final HumanityClient client;

    private PlayerState state;
    private int score;
    private ClientHand hand;

    public ClientClientDefinition(HumanityClient client) {
        this.client = Preconditions.checkNotNull(client, "client");
        this.client.getPacketHandler().registerHandlers(this);
        this.hand = new ClientHand(this.client);
    }

    @Override
    public PlayerState getPlayerState() {
        return this.state;
    }

    @Override
    public IHumanityHand getPlayerHand() {
        return this.hand;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Handler(priority = HandlerPriority.MONITOR)
    public void onPlayerStateChange(Packet05PlayerStateChange packet) {
        this.state = packet.getTo();
    }
}
