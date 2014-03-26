package com.ttaylorr.dev.humanity.client.client.player;

import com.google.common.base.Preconditions;

import com.ttaylorr.dev.humanity.client.Bootstrap;
import com.ttaylorr.dev.humanity.server.client.player.PlayerState;
import com.ttaylorr.dev.humanity.server.client.player.definition.IPlayerDefinition;
import com.ttaylorr.dev.humanity.server.packets.core.Packet10PlayerUpdate;

public abstract class ClientPlayerDefinition implements IPlayerDefinition {

    protected final String name;
    protected int score;
    protected PlayerState state;

    protected ClientPlayerDefinition(String name) {
        this.name = name;
        this.score = 0;
        this.state = PlayerState.OBSERVING;
    }

    @Override
    public PlayerState getPlayerState() {
        return state;
    }

    public static ClientPlayerDefinition buildObserved(Packet10PlayerUpdate packet) {
        Preconditions.checkNotNull(packet, "player update");
        Preconditions.checkArgument(!packet.getClientUuid().equals(Bootstrap.getClient().getDefnition().getUUID()), "invalid player"); // make sure this Player actually could be observed

        ClientPlayerDefinition ret = new ObservedClientPlayerDefinition(packet.getName());
        ret.score = packet.getScore();
        ret.state = packet.getState();
        return ret;
    }

    public static void updateOwnedPlayer(Packet10PlayerUpdate packet) {
        Preconditions.checkNotNull(packet, "player update");
        Preconditions.checkArgument(!packet.getClientUuid().equals(Bootstrap.getClient().getDefnition().getUUID()), "invalid player"); // make sure this Player actually could be observed


    }
}
