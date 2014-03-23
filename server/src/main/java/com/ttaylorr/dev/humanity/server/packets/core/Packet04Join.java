package com.ttaylorr.dev.humanity.server.packets.core;

import com.ttaylorr.dev.humanity.server.packets.Packet;

import java.io.Serializable;
import java.util.UUID;

public class Packet04Join extends Packet {

    private JoinState state;
    private String reason;
    private final UUID id;

    public Packet04Join(JoinState state, String reason, UUID id) {
        this.state = state;
        this.reason = reason;
        this.id = id;
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
