package net.humanity_game.server.packets.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.humanity_game.server.client.ClientConnection;
import net.humanity_game.server.packets.Packet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Server->Client
 */
public class Packet09UpdatePlayerList extends Packet {

    final List<PlayerUpdate> playerUpdates;

    public Packet09UpdatePlayerList() {
        super(null);
        playerUpdates = new ArrayList<>();
    }

    public Packet09UpdatePlayerList( Collection<ClientConnection> clients) {
        this();
        for (ClientConnection client : clients) {
            if (client == null) // not preconditions to allow more flexibility.
                continue;
            else
                playerUpdates.add(new PlayerUpdate(client, Type.NEW_JOIN));
        }
        Preconditions.checkState(playerUpdates.size() > 0, "no players in player update list");
    }

    public Packet09UpdatePlayerList(ClientConnection client) {
        this(Lists.newArrayList(client));
    }

    public Packet09UpdatePlayerList(ClientConnection client, Type type) {
        this();
        Preconditions.checkNotNull(client, "client");
        Preconditions.checkNotNull(type, "type");
        playerUpdates.add(new PlayerUpdate(client, type));
    }

    /**
     * List implementer can be specified so that the most appropriate kind is always used, or, default to ArrayList.
     *
     * @param model
     */
    public Packet09UpdatePlayerList(List<PlayerUpdate> model) {
        super(null);
        playerUpdates = model;
    }

    public void addPlayerUpdate(ClientConnection connection, Type type) {
        Preconditions.checkNotNull(connection, "client");
        playerUpdates.add(new PlayerUpdate(connection, type));
    }


    public ImmutableList<PlayerUpdate> getUpdatedPlayers() {
        return ImmutableList.copyOf(playerUpdates);
    }


    public static enum Type {
        REMOVAL,
        PREVIOUSLY_CONNECTED,
        NEW_JOIN;
    }

    public class PlayerUpdate implements Serializable{
        public PlayerUpdate(ClientConnection who, Type type) {
            clientId = who.getClientId();
            this.type = Preconditions.checkNotNull(type, "type");
            this.name = who.getName();
            this.host = who.getConnection().getInetAddress().getHostAddress();
            this.port = who.getConnection().getPort();
        }

        private final Type type;
        private final UUID clientId;
        private final String name;
        private final String host;
        private final int port;

        public Type getType() {
            return type;
        }

        public UUID getClientId() {
            return clientId;
        }

        public String getName() {
            return name;
        }

        public String getHost() {
            return host;
        }

        public int getPort() {
            return port;
        }
        // todo add any other data needed for constructing a HumanityClient.
    }
}
