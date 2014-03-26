package com.ttaylorr.dev.humanity.server.packets.core;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;
import com.ttaylorr.dev.humanity.server.client.MaskedClientConnection;
import com.ttaylorr.dev.humanity.server.packets.Packet;

public class Packet09MaskedJoin extends Packet {

    private final MaskedClientConnection who;
    private final Type type;

    public Packet09MaskedJoin(ClientConnection who, Type type) {
        this.who = new MaskedClientConnection(who);
        this.type = Preconditions.checkNotNull(type, "type");
    }

    public MaskedClientConnection getWho() {
        return this.who;
    }

    public Type getType() {
        return this.type;
    }

    public static enum Type {
        PREVIOUSLY_CONNECTED,

        NEW_JOIN;
    }
}
