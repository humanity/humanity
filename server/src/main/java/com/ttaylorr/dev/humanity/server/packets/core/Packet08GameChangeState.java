package com.ttaylorr.dev.humanity.server.packets.core;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.game.state.GameState;
import com.ttaylorr.dev.humanity.server.packets.Packet;

public class Packet08GameChangeState extends Packet {

    private final GameState from;
    private final GameState to;

    public Packet08GameChangeState(GameState from, GameState to) {
        this.from = Preconditions.checkNotNull(from, "from");
        this.to = Preconditions.checkNotNull(to, "to");
    }

    public GameState getFrom() {
        return this.from;
    }

    public GameState getTo() {
        return this.to;
    }
}
