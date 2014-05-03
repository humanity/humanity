package net.humanity_game.server.listeners;

import com.google.common.base.Preconditions;
import net.humanity_game.server.HumanityServer;
import net.humanity_game.server.client.ClientConnection;
import net.humanity_game.server.handlers.Handler;
import net.humanity_game.server.handlers.HandlerPriority;
import net.humanity_game.server.handlers.Listenable;
import net.humanity_game.server.packets.core.Packet02Handshake;
import net.humanity_game.server.packets.core.Packet04Join;
import net.humanity_game.server.packets.core.Packet09UpdatePlayerList;

public class HandshakeListener implements Listenable {

    private HumanityServer server;

    public HandshakeListener(HumanityServer server) {
        this.server = Preconditions.checkNotNull(server);
    }

    @Handler(priority = HandlerPriority.NORMAL)
    public void onClientHandshake(Packet02Handshake handshake, ClientConnection connectingClient) {
        this.server.getLogger().info("Received handshake from client named: \"{}\".", handshake.getName());

        Packet04Join packet = new Packet04Join(Packet04Join.JoinState.ALLOWED, "Welcome to the server!", connectingClient.getClientId());
        connectingClient.sendPacket(packet);

        this.server.getGame().getWhiteCardDeck().dealCards(connectingClient);

        for(ClientConnection connectedClient : this.server.getClientManager().getConnectedClients()) {
            if (!(connectedClient.getClientId().equals(connectingClient.getClientId()))) {
                connectedClient.sendPacket(new Packet09UpdatePlayerList(connectingClient, Packet09UpdatePlayerList.Type.NEW_JOIN));
            }
        }

        for(ClientConnection connectedClient : this.server.getClientManager().getConnectedClients()) {
            if (!(connectedClient.getClientId().equals(connectingClient.getClientId()))) {
                connectingClient.sendPacket(new Packet09UpdatePlayerList(connectedClient, Packet09UpdatePlayerList.Type.PREVIOUSLY_CONNECTED));
            }
        }
    }
}
