package net.humanity_game.server.packets.core;

import com.google.common.base.Preconditions;
import net.humanity_game.server.packets.Packet;

import java.util.UUID;

public class Packet04Join extends Packet {

    private JoinState state;
    private String reason;
    private final UUID id;

    public Packet04Join(JoinState state, String reason, UUID uuid) {
        this.state = state;
        this.reason = reason;
        this.id = Preconditions.checkNotNull(uuid, "invalid client uuid");
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
