package com.ttaylorr.dev.humanity.server.packets.masked.core;

import com.ttaylorr.dev.humanity.server.client.player.PlayerState;
import com.ttaylorr.dev.humanity.server.packets.masked.AbstractMaskedPacket;

import java.util.UUID;

public class Packet12MaskedPlayerStateChange extends AbstractMaskedPacket {

    private final PlayerState to;

    public Packet12MaskedPlayerStateChange(UUID target, PlayerState to) {
        super(target);
        this.to = to;
    }

    public PlayerState getTo() {
        return this.to;
    }

}
