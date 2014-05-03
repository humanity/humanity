package net.humanity_game.server.packets.masked.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.server.client.ClientConnection;
import net.humanity_game.server.packets.masked.AbstractMaskedPacket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Server->Client
 */
public class Packet09UpdatePlayerList extends AbstractMaskedPacket {

    final List<PlayerUpdate> playerUpdates;

    public Packet09UpdatePlayerList(ClientConnection target, Collection<ClientConnection> clients) {
        super(target);
        playerUpdates = new ArrayList(clients.size());
        for (ClientConnection client : clients) {
            if (client == null) // not preconditions to allow more flexibility.
                continue;
            else
                playerUpdates.add(new PlayerUpdate(client, Type.NEW_JOIN));
        }

        Preconditions.checkState(playerUpdates.size() > 0, "no players in player update list");
    }

    public void updateExistingPlayer(ClientConnection client) {
        Preconditions.checkNotNull(client, "client");
        playerUpdates.add(new PlayerUpdate(client, Type.PREVIOUSLY_CONNECTED));
    }

    public void updateExistingPlayer(Collection<ClientConnection> clients) {
        for (ClientConnection client : clients) {
            if (client == null) // not preconditions to allow more flexibility.
                continue;
            else
                playerUpdates.add(new PlayerUpdate(client, Type.PREVIOUSLY_CONNECTED));
        }
    }

   public ImmutableList<PlayerUpdate> getUpdatedPlayers() {
        return ImmutableList.copyOf(playerUpdates);
    }


    public static enum Type {
        PREVIOUSLY_CONNECTED,

        NEW_JOIN;
    }

    public class PlayerUpdate {
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
