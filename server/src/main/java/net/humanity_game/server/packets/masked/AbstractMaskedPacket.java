package net.humanity_game.server.packets.masked;

import com.google.common.base.Preconditions;
import net.humanity_game.server.client.ClientConnection;
import net.humanity_game.server.packets.Packet;

import java.util.UUID;

public class AbstractMaskedPacket extends Packet {

    private final UUID target;

    public AbstractMaskedPacket(UUID target) {
        this.target = Preconditions.checkNotNull(target, "null uuid target");
    }

    public AbstractMaskedPacket(ClientConnection realTarget) {
        this.target = Preconditions.checkNotNull(realTarget.getClientId(), "null target uuid");
    }

    public UUID getTarget() {
        return target;
    }
}
