package net.humanity_game.server.packets.core;

import com.google.common.base.Preconditions;
import net.humanity_game.server.client.player.PlayerState;
import net.humanity_game.server.packets.Packet;

import java.util.UUID;

public class Packet05PlayerStateChange extends Packet {

    private final PlayerState to;

    public Packet05PlayerStateChange(UUID id, PlayerState to) {
        super(id);
        this.to = Preconditions.checkNotNull(to, "state");
    }

    public PlayerState getTo() {
        return this.to;
    }
}
