package com.ttaylorr.dev.humanity.server.listeners;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.HumanityServer;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;
import com.ttaylorr.dev.humanity.server.handlers.Handler;
import com.ttaylorr.dev.humanity.server.handlers.HandlerPriority;
import com.ttaylorr.dev.humanity.server.handlers.Listenable;
import com.ttaylorr.dev.humanity.server.packets.core.Packet01KeepAlive;

public class KeepAliveListener implements Listenable {

    private HumanityServer server;

    public KeepAliveListener(HumanityServer server) {
        this.server = Preconditions.checkNotNull(server, "server");
    }

    @Handler(priority = HandlerPriority.MONITOR)
    public void onKeepAliveIncoming(Packet01KeepAlive packet, ClientConnection client) {
        client.sendPacket(new Packet01KeepAlive(packet.getUuid()));
    }
}
