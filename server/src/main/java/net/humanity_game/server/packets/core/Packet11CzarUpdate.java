package net.humanity_game.server.packets.core;

import com.google.common.base.Preconditions;
import net.humanity_game.server.packets.Packet;

import java.util.UUID;

public class Packet11CzarUpdate extends Packet {

    private UUID newCzar;


    public Packet11CzarUpdate(UUID uuid, UUID newCzar) {
        super(uuid);
        this.newCzar = Preconditions.checkNotNull(newCzar, "czar");
    }

    /**
     * Send this packet to all clients.
     *
     * @param newCzar
     */
    public Packet11CzarUpdate(UUID newCzar) {
        this(null, newCzar);
    }

    public UUID getNewCzar() {
        return newCzar;
    }

}
