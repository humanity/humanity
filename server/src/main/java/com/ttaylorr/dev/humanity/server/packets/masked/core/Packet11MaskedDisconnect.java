package com.ttaylorr.dev.humanity.server.packets.masked.core;

import com.ttaylorr.dev.humanity.server.client.MaskedClientConnection;
import com.ttaylorr.dev.humanity.server.packets.masked.AbstractMaskedPacket;

public class Packet11MaskedDisconnect extends AbstractMaskedPacket {

    public Packet11MaskedDisconnect(MaskedClientConnection who) {
        super(who);
    }

}
