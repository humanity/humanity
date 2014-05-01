package net.humanity_game.server.packets.masked.core;

import com.google.common.base.Preconditions;
import net.humanity_game.server.client.ClientConnection;
import net.humanity_game.server.packets.masked.AbstractMaskedPacket;

public class Packet09MaskedJoin extends AbstractMaskedPacket {

    private final Type type;

    public Packet09MaskedJoin(ClientConnection who, Type type) {
        super(who);
        this.type = Preconditions.checkNotNull(type, "type");
    }

    public Type getType() {
        return this.type;
    }

    public static enum Type {
        PREVIOUSLY_CONNECTED,

        NEW_JOIN;
    }
}
