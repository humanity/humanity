package com.ttaylorr.dev.humanity.server.packets.masked.core;

import com.ttaylorr.dev.humanity.server.client.MaskedClientConnection;
import com.ttaylorr.dev.humanity.server.client.player.PlayerState;
import com.ttaylorr.dev.humanity.server.packets.masked.AbstractMaskedPacket;

public class Packet12MaskedPlayerStateChange extends AbstractMaskedPacket {

    private final PlayerState to;

    public Packet12MaskedPlayerStateChange(MaskedClientConnection target, PlayerState to) {
        super(target);

        this.to = to;
    }

    public PlayerState getTo() {
        return this.to;
    }

}
