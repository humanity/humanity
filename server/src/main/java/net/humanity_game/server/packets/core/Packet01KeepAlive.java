package net.humanity_game.server.packets.core;

import com.google.common.base.Preconditions;
import net.humanity_game.server.packets.Packet;

import java.util.UUID;

public class Packet01KeepAlive extends Packet {

    private final UUID uuid;

    public Packet01KeepAlive() {
        this(UUID.randomUUID());
    }

    public Packet01KeepAlive(UUID uuid) {
        this.uuid = Preconditions.checkNotNull(uuid, "uuid");
    }

    public UUID getUuid() {
        return this.uuid;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Packet01KeepAlive) {
            return ((Packet01KeepAlive) o).getUuid().equals(this.uuid);
        } else {
            return false;
        }
    }
}
