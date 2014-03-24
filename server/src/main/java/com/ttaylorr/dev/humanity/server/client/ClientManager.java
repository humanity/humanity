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
        UUID clientId = UUID.randomUUID();
        this.connectedClients.put(client, clientId);

        this.logger.info("Connect client with ID: {}", clientId);

        IncomingPacketListener packetListener = new IncomingPacketListener(client, this.server);
        Thread thread = new Thread(packetListener);
        thread.setName("IncomingPacketListener-" + clientId.toString());
        thread.start();

        this.clientPacketListeners.put(client, new AbstractMap.SimpleEntry<>(packetListener, thread));
        this.server.getOutboundPackets().connectClient(client);
    }

    public void disconnectClient(ClientConnection client) {
        this.logger.info("Removing and closing thread for client: {}", this.getUUIDForClient(client));
        this.connectedClients.remove(client);

        Map.Entry<IncomingPacketListener, Thread> value = this.clientPacketListeners.remove(client);
        value.getValue().stop();

        this.server.getOutboundPackets().disconnectClient(client);
    }

    public void disconnectAll(HumanityServer server) {
        if (this.server.equals(server)) {
            this.server.getOutboundPackets().sendPacket(new Packet03Disconnect(), this.getConnectedClients());
        }
    }

    public IncomingPacketListener getListenerFor(ClientConnection client) {
        if (this.clientPacketListeners.get(client) == null) {
            throw new IllegalArgumentException("that client is not connected");
        }

        return this.clientPacketListeners.get(client).getKey();
    }

    public ClientConnection getClientById(UUID id) {
        for (Map.Entry<ClientConnection, UUID> entry : this.connectedClients.entrySet()) {
            if (entry.getValue().equals(id)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public UUID getUUIDForClient(ClientConnection clientConnection) {
        return this.connectedClients.get(clientConnection);
    }

    public ImmutableList<ClientConnection> getConnectedClients() {
        return ImmutableList.copyOf(this.connectedClients.keySet());
    }
}
