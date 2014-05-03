package net.humanity_game.server.packets.core;

import net.humanity_game.server.packets.Packet;

import java.util.UUID;

public class Packet01KeepAlive extends Packet {

    private final UUID keepAliveId;

    public Packet01KeepAlive(UUID client) {
        this(client, UUID.randomUUID());
    }

    public Packet01KeepAlive(UUID client, UUID keepAliveId) {
        super(client);
        this.keepAliveId = keepAliveId;
    }

    public UUID getKeepAliveId() {
        return this.keepAliveId;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Packet01KeepAlive) {
            return ((Packet01KeepAlive) o).getKeepAliveId().equals(this.getKeepAliveId());
        } else {
            return false;
        }
    }
}
