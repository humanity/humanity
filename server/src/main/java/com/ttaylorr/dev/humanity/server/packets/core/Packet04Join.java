package com.ttaylorr.dev.humanity.server.packets.core;

import com.ttaylorr.dev.humanity.server.client.MaskedClientConnection;
import com.ttaylorr.dev.humanity.server.packets.Packet;

import java.util.UUID;

public class Packet04Join extends Packet {

    private JoinState state;
    private String reason;
    private final UUID id;

    public Packet04Join(JoinState state, String reason, MaskedClientConnection client) {
        this.state = state;
        this.reason = reason;
        this.id = client.getClientId();
    }

    public JoinState getState() {
        return state;
    }

    public String getReason() {
        return reason;
    }

    public final UUID getServerAssignedUUID() {
        return this.id;
    }

    public static enum JoinState {
        ALLOWED, DENIED;
    }

}
