package com.ttaylorr.dev.humanity.server.listeners;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.HumanityServer;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;
import com.ttaylorr.dev.humanity.server.handlers.Handler;
import com.ttaylorr.dev.humanity.server.handlers.HandlerPriority;
import com.ttaylorr.dev.humanity.server.handlers.Listenable;
import com.ttaylorr.dev.humanity.server.packets.core.Packet02Handshake;
import com.ttaylorr.dev.humanity.server.packets.core.Packet04Join;

public class HandshakeListener implements Listenable {

    private HumanityServer server;

    public HandshakeListener(HumanityServer server) {
        this.server = Preconditions.checkNotNull(server);
    }

    @Handler(priority = HandlerPriority.NORMAL)
    public void onClientHandshake(Packet02Handshake handshake, ClientConnection client) {
        this.server.getLogger().info("Received handshake from client named: \"{}\".", handshake.getName());

        client.sendObject(new Packet04Join(Packet04Join.JoinState.ALLOWED, "Welcome to the server!"));

    }
}
