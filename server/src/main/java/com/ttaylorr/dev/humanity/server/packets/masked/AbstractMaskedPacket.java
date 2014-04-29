package com.ttaylorr.dev.humanity.server.packets.masked;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;
import com.ttaylorr.dev.humanity.server.packets.Packet;

import java.util.UUID;

public class AbstractMaskedPacket extends Packet {

    private final UUID target;

    public AbstractMaskedPacket(UUID target) {
        this.target = Preconditions.checkNotNull(target, "null uuid target");
    }

    public AbstractMaskedPacket(ClientConnection realTarget) {
        this.target = Preconditions.checkNotNull(realTarget.getClientId(), "null target uuid");
    }

    public UUID getTarget() {
        return target;
    }
}
