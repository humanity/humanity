package com.ttaylorr.dev.humanity.server.packets.core;

import com.ttaylorr.dev.humanity.server.packets.Packet;

import java.io.Serializable;

public class Packet04Join extends Packet implements Serializable {

    private JoinState state;
    private String reason;

    public Packet04Join(JoinState state, String reason) {
        this.state = state;
        this.reason = reason;
    }

    public JoinState getState() {
        return state;
    }

    public String getReason() {
        return reason;
    }

    public static enum JoinState {
        ALLOWED, DENIED;
    }

}
