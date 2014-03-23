package com.ttaylorr.dev.humanity.server.packets.core;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.client.state.PlayerState;
import com.ttaylorr.dev.humanity.server.packets.Packet;

public class Packet05PlayerStateChange extends Packet {

    private final PlayerState to;

    public Packet05PlayerStateChange(PlayerState to) {
        this.to = Preconditions.checkNotNull(to, "state");
    }

    public PlayerState getTo() {
        return this.to;
    }
}
