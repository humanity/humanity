package com.ttaylorr.dev.humanity.client.client.player;

import com.ttaylorr.dev.humanity.server.client.player.PlayerState;
import com.ttaylorr.dev.humanity.server.client.player.definition.IPlayerDefinition;

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
}
