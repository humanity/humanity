package com.ttaylorr.dev.humanity.client.definition;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.client.client.HumanityClient;
import com.ttaylorr.dev.humanity.client.cards.ClientHumanityHand;
import com.ttaylorr.dev.humanity.client.client.player.OwnedClientPlayerDefinition;
import com.ttaylorr.dev.humanity.server.cards.hand.IHumanityHand;
import com.ttaylorr.dev.humanity.server.client.definition.IClientDefinition;
import com.ttaylorr.dev.humanity.server.client.player.PlayerState;
import com.ttaylorr.dev.humanity.server.handlers.Handler;
import com.ttaylorr.dev.humanity.server.handlers.HandlerPriority;
import com.ttaylorr.dev.humanity.server.handlers.Listenable;
import com.ttaylorr.dev.humanity.server.packets.core.Packet05PlayerStateChange;

import java.util.UUID;

public class ClientClientDefinition implements IClientDefinition, Listenable {

    private final HumanityClient client;

    private OwnedClientPlayerDefinition player;
    private UUID clientUUID;

    public ClientClientDefinition(HumanityClient client) {
        this.client = Preconditions.checkNotNull(client, "client");
        this.client.getPacketHandler().registerHandlers(this);
        // TODO construct initial OwnedPlayer here.
    }

    @Override
    public PlayerState getPlayerState() {
        return this.player.getPlayerState();
    }



    @Handler(priority = HandlerPriority.MONITOR)
    public void onPlayerStateChange(Packet05PlayerStateChange packet) {
        this.player.setPlayerState(packet.getTo());
    }

    public UUID getUUID() {
        return clientUUID;
    }

}
