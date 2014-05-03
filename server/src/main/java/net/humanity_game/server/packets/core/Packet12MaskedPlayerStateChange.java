package net.humanity_game.server.packets.core;

import net.humanity_game.server.client.player.PlayerState;
import net.humanity_game.server.packets.AbstractMaskedPacket;

import java.util.UUID;

public class Packet12MaskedPlayerStateChange extends AbstractMaskedPacket {

    private final PlayerState to;

    public Packet12MaskedPlayerStateChange(UUID target, PlayerState to) {
        super(target);
        this.to = to;
    }

    public PlayerState getTo() {
        return this.to;
    }

}
