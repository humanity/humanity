package com.ttaylorr.dev.humanity.server.packets.core;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.client.MaskedClientConnection;
import com.ttaylorr.dev.humanity.server.packets.Packet;

public class Packet11MaskedDisconnect extends Packet {

    private final MaskedClientConnection who;

    public Packet11MaskedDisconnect(MaskedClientConnection who) {
        this.who = Preconditions.checkNotNull(who, "who");
    }

    public final MaskedClientConnection getWho() {
        return this.who;
    }
}
