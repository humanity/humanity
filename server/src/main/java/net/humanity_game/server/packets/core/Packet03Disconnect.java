package net.humanity_game.server.packets.core;

import net.humanity_game.server.packets.Packet;

import java.util.UUID;

public class Packet03Disconnect extends Packet {

    public Packet03Disconnect(UUID uuid) {
        super(uuid);
    }

}
