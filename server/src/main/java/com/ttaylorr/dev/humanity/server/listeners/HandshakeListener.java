package com.ttaylorr.dev.humanity.server.listeners;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.HumanityServer;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;
import com.ttaylorr.dev.humanity.server.client.player.MaskedClientConnection;
import com.ttaylorr.dev.humanity.server.handlers.Handler;
import com.ttaylorr.dev.humanity.server.handlers.HandlerPriority;
import com.ttaylorr.dev.humanity.server.handlers.Listenable;
import com.ttaylorr.dev.humanity.server.packets.core.Packet02Handshake;
import com.ttaylorr.dev.humanity.server.packets.core.Packet04Join;
import com.ttaylorr.dev.humanity.server.packets.core.Packet09MaskedJoin;

public class HandshakeListener implements Listenable {

    private HumanityServer server;

    public HandshakeListener(HumanityServer server) {
        this.server = Preconditions.checkNotNull(server);
    }

    @Handler(priority = HandlerPriority.NORMAL)
    public void onClientHandshake(Packet02Handshake handshake, ClientConnection connectingClient) {
        this.server.getLogger().info("Received handshake from client named: \"{}\".", handshake.getName());

        Packet04Join packet = new Packet04Join(Packet04Join.JoinState.ALLOWED, "Welcome to the server!", new MaskedClientConnection(connectingClient));
        connectingClient.sendPacket(packet);

        this.server.getGame().getWhiteCardDeck().dealCards(connectingClient);

        for(ClientConnection connectedClient : this.server.getClientManager().getConnectedClients()) {
            if (!(connectedClient.getClientId().equals(connectingClient.getClientId()))) {
                connectedClient.sendPacket(new Packet09MaskedJoin(connectingClient, Packet09MaskedJoin.Type.NEW_JOIN));
            }
        }

        for(ClientConnection connectedClient : this.server.getClientManager().getConnectedClients()) {
            if (!(connectedClient.getClientId().equals(connectingClient.getClientId()))) {
                connectingClient.sendPacket(new Packet09MaskedJoin(connectedClient, Packet09MaskedJoin.Type.PREVIOUSLY_CONNECTED));
            }
        }
    }
}
