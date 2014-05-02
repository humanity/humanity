package net.humanity_game.server.packets.core;

import net.humanity_game.server.packets.Packet;

import java.util.UUID;

/**
 * Join = the packet sent to a specific client saying that it has in fact joined.
 * MaskedJoin = the packet alerting all other clients to a client's connection.
 *
 * My question is: should this instead use the upcoming separate-handlers by client system?
 */
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
