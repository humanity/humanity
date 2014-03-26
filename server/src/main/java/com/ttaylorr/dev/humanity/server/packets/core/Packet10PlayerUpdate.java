package com.ttaylorr.dev.humanity.server.packets.core;

import com.ttaylorr.dev.humanity.server.client.player.PlayerState;
import com.ttaylorr.dev.humanity.server.packets.Packet;

import java.util.UUID;

/**
 * Updates a client's understanding of a Player, but only to the extent that is defined in IPlayerDefinition.
 */
public class Packet10PlayerUpdate extends Packet {

    private final int score;
    private final String name;
    private final PlayerState state;
    private final UUID clientUuid;


    public Packet10PlayerUpdate(int score, String name, PlayerState state, UUID uuid) {
        this.score = score;
        this.name = name;
        this.state = state;
        this.clientUuid = uuid;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public PlayerState getState() {
        return state;
    }

    public UUID getClientUuid() {
        return clientUuid;
    }
}
