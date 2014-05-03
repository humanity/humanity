package net.humanity_game.server.packets.core;

import net.humanity_game.server.packets.Packet;

import java.util.UUID;

public class Packet04Join extends Packet {

    private JoinState state;
    private String reason;

    public Packet04Join(JoinState state, String reason, UUID uuid) {
        super(uuid);
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
