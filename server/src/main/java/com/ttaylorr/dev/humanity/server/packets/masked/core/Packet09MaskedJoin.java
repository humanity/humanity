package com.ttaylorr.dev.humanity.server.packets.masked.core;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;
import com.ttaylorr.dev.humanity.server.packets.masked.AbstractMaskedPacket;

public class Packet09MaskedJoin extends AbstractMaskedPacket {

    private final Type type;

    public Packet09MaskedJoin(ClientConnection who, Type type) {
        super(who);
        this.type = Preconditions.checkNotNull(type, "type");
    }

    public Type getType() {
        return this.type;
    }

    public static enum Type {
        PREVIOUSLY_CONNECTED,

        NEW_JOIN;
    }
}
