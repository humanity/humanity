package com.ttaylorr.dev.humanity.server.packets.masked;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;
import com.ttaylorr.dev.humanity.server.client.MaskedClientConnection;
import com.ttaylorr.dev.humanity.server.packets.Packet;

public class AbstractMaskedPacket extends Packet {

    private final MaskedClientConnection target;

    public AbstractMaskedPacket(MaskedClientConnection target) {
        this.target = Preconditions.checkNotNull(target, "masked target");
    }

    public AbstractMaskedPacket(ClientConnection realTarget) {
        this.target = MaskedClientConnection.fromClient(Preconditions.checkNotNull(realTarget, "real target"));
    }

    public MaskedClientConnection getTarget() {
        return this.target;
    }
}
