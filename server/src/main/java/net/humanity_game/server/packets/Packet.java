package net.humanity_game.server.packets;

import net.humanity_game.server.client.ClientConnection;
import net.humanity_game.server.client.ServerClientManager;

import java.io.Serializable;
import java.util.UUID;

public abstract class Packet implements Serializable {

    /** This is the UUID of the client that it is intended for */
    private UUID uuid;

    protected Packet(UUID uuid) {
        // allow UUID to be null -> send to all clients
        this.uuid = uuid;
    }

    public UUID getClientId() {
        return this.uuid;
    }

    public void sendToAll(ServerClientManager clientManager) {
        for (ClientConnection clientConnection : clientManager.getConnectedClients()) {
            clientConnection.sendPacket(this);
        }
    }
}
