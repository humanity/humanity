package com.ttaylorr.dev.humanity.server.client;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.ttaylorr.dev.humanity.server.HumanityServer;
import com.ttaylorr.dev.humanity.server.packets.core.Packet03Disconnect;
import com.ttaylorr.dev.humanity.server.queue.core.IncomingPacketListener;
import com.ttaylorr.dev.logger.Logger;
import com.ttaylorr.dev.logger.LoggerProvider;

import java.util.*;

public class ClientManager {

    private HumanityServer server;

    private Map<ClientConnection, UUID> connectedClients;
    private Map<ClientConnection, Map.Entry<IncomingPacketListener, Thread>> clientPacketListeners;

    private Logger logger;

    public ClientManager(HumanityServer server) {
        this.server = Preconditions.checkNotNull(server, "server");
        this.logger = LoggerProvider.putLogger(this.getClass());
    }

    public void setup() {
        this.connectedClients = new HashMap<>();
        this.clientPacketListeners = new HashMap<>();
    }

    public void connectClient(ClientConnection client) {
        this.connectedClients.put(client, UUID.randomUUID());

        this.logger.info("Connecting a new client (#{})", Integer.valueOf(this.connectedClients.size()));

        IncomingPacketListener packetListener = new IncomingPacketListener(client, this.server);
        Thread thread = new Thread(packetListener);
        thread.start();

        this.clientPacketListeners.put(client, new AbstractMap.SimpleEntry<>(packetListener, thread));
    }

    public void disconnectClient(ClientConnection client) {
        this.connectedClients.remove(client);

        this.logger.info("Removing client (#{}) and closing thread...", Integer.valueOf(this.connectedClients.size() + 1));

        Map.Entry<IncomingPacketListener, Thread> value = this.clientPacketListeners.remove(client);
        value.getValue().stop();
    }

    public void disconnectAll(HumanityServer server) {
        if (this.server.equals(server)) {
            this.server.getOutboundPackets().addPacket(new Packet03Disconnect(), this.getConnectedClients());
        }
    }

    public IncomingPacketListener getListenerFor(ClientConnection client) {
        if (this.clientPacketListeners.get(client) == null) {
            throw new IllegalArgumentException("that client is not connected");
        }

        return this.clientPacketListeners.get(client).getKey();
    }

    public ImmutableList<ClientConnection> getConnectedClients() {
        return ImmutableList.copyOf(this.connectedClients.keySet());
    }
}
