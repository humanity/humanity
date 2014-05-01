package net.humanity_game.server.packets.core;

import com.google.common.base.Preconditions;
import net.humanity_game.server.client.player.PlayerState;
import net.humanity_game.server.packets.Packet;

public class Packet05PlayerStateChange extends Packet {

    private final PlayerState to;

    public Packet05PlayerStateChange(PlayerState to) {
        this.to = Preconditions.checkNotNull(to, "state");
    }

    public PlayerState getTo() {
        return this.to;
    }
}
