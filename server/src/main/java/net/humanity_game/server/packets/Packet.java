package net.humanity_game.server.packets;

import com.google.common.base.Preconditions;

import java.io.Serializable;
import java.util.UUID;

public abstract class Packet implements Serializable {

    /** This is the UUID of the client that it is intended for */
    private UUID uuid;

    protected Packet(UUID uuid) {
        this.uuid = Preconditions.checkNotNull(uuid, "uuid");
    }

    public UUID getClientId() {
        return this.uuid;
    }
}
