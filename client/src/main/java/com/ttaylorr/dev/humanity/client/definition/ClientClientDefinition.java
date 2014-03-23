package com.ttaylorr.dev.humanity.client.definition;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.client.HumanityClient;
import com.ttaylorr.dev.humanity.server.client.definition.IClientDefinition;
import com.ttaylorr.dev.humanity.server.client.state.PlayerState;
import com.ttaylorr.dev.humanity.server.handlers.Handler;
import com.ttaylorr.dev.humanity.server.handlers.HandlerPriority;
import com.ttaylorr.dev.humanity.server.handlers.Listenable;
import com.ttaylorr.dev.humanity.server.packets.core.Packet05PlayerStateChange;

public class ClientClientDefinition implements IClientDefinition, Listenable {

    private final HumanityClient client;

    private PlayerState state;

    public ClientClientDefinition(HumanityClient client) {
        this.client = Preconditions.checkNotNull(client, "client");
        this.client.getPacketHandler().registerHandlers(this);
    }

    @Override
    public PlayerState getPlayerState() {
        return this.state;
    }

    @Handler(priority = HandlerPriority.MONITOR)
    public void onPlayerStateChange(Packet05PlayerStateChange packet) {
        this.state = packet.getTo();
    }
}
