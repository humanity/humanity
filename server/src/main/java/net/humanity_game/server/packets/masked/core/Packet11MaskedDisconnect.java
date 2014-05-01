package net.humanity_game.server.packets.masked.core;

import net.humanity_game.server.packets.masked.AbstractMaskedPacket;

import java.util.UUID;

public class Packet11MaskedDisconnect extends AbstractMaskedPacket {

    public Packet11MaskedDisconnect(UUID uuid) {
        super(uuid);
    }

}
