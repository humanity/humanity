package net.humanity_game.server.packets.masked.core;

import com.google.common.base.Preconditions;
import net.humanity_game.server.client.ClientConnection;
import net.humanity_game.server.packets.masked.AbstractMaskedPacket;

public class Packet09MaskedJoin extends AbstractMaskedPacket {

    private final Type type;

    private final String name;
    private final String host;
    private final int port;
    // todo add any other data needed for constructing a HumanityClient.


    public Packet09MaskedJoin(ClientConnection who, Type type) {
        super(who);
        this.type = Preconditions.checkNotNull(type, "type");
        this.name = who.getName();
        this.host = who.getConnection().getInetAddress().getHostAddress();
        this.port = who.getConnection().getPort();
    }

    public static enum Type {
        PREVIOUSLY_CONNECTED,

        NEW_JOIN;
    }

    public Type getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }
}
