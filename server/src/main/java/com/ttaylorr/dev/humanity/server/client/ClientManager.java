package com.ttaylorr.dev.humanity.server.client;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.ttaylorr.dev.humanity.server.HumanityServer;
import com.ttaylorr.dev.humanity.server.packets.core.Packet03Disconnect;
import com.ttaylorr.dev.humanity.server.packets.masked.core.Packet11MaskedDisconnect;
import com.ttaylorr.dev.humanity.server.queue.IncomingPacketListener;
import com.ttaylorr.dev.logger.Logger;
import com.ttaylorr.dev.logger.LoggerProvider;

import java.util.*;

public class ClientManager implements IClientManager<ClientConnection> {

    private HumanityServer server;

    private List<ClientConnection> connectedClients;
    private Map<ClientConnection, IncomingPacketListener> clientPacketListeners;

    private Logger logger;

    public ClientManager(HumanityServer server) {
        this.server = Preconditions.checkNotNull(server, "server");
        this.logger = LoggerProvider.putLogger(this.getClass());
    }

    public void setup() {
        this.connectedClients = new ArrayList<>();
        this.clientPacketListeners = new HashMap<>();
    }

    public void connectClient(ClientConnection client) {
        this.connectedClients.add(client);

        this.logger.info("Connect client with ID: {}", client.getClientId());

        client.openDequeue();
        IncomingPacketListener packetListener = new IncomingPacketListener(client, this.server);
        Thread thread = new Thread(packetListener);
        thread.setName("IncomingPacketListener-" + client.getClientId().toString());
        thread.start();

        this.clientPacketListeners.put(client, packetListener);
    }

    public void disconnectClient(ClientConnection client) {
        this.logger.info("Removing and closing thread for client: {}", this.getUUIDForClient(client));
        this.connectedClients.remove(client);

        IncomingPacketListener value = this.clientPacketListeners.remove(client);
        value.requestClose();

        Packet11MaskedDisconnect maskedDisconnectPacket = new Packet11MaskedDisconnect(client.getClientId());
        for (ClientConnection connected : this.connectedClients) {
            this.logger.debug("fu");
            connected.sendPacket(maskedDisconnectPacket);
        }

        client.closeDequeue();
    }

    public void disconnectAll(HumanityServer server) {
        if (this.server.equals(server)) {
            for (ClientConnection client : this.server.getClientManager().getConnectedClients()) {
                client.sendPacket(new Packet03Disconnect());
            }
        }
    }

    public IncomingPacketListener getListenerFor(ClientConnection client) {
        if (this.clientPacketListeners.get(client) == null) {
            throw new IllegalArgumentException("that client is not connected");
        }

        return this.clientPacketListeners.get(client);
    }

    public ClientConnection getClientById(UUID id) {
        for (ClientConnection client : this.connectedClients) {
            if (client.getClientId().equals(id)) {
                return client;
            }
        }
        return null;
    }

    public UUID getUUIDForClient(ClientConnection clientConnection) {
        for (ClientConnection client : this.connectedClients) {
            if (client == clientConnection) {
                return client.getClientId();
            }
        }
        return null;
    }

    public ImmutableList<ClientConnection> getConnectedClients() {
        return ImmutableList.copyOf(this.connectedClients);
    }
}
