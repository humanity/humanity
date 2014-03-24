package com.ttaylorr.dev.humanity.server.queue.core;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.HumanityServer;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;
import com.ttaylorr.dev.humanity.server.packets.Packet;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class OutboundPacketQueue implements Runnable {

    private final Map<ClientConnection, ConcurrentLinkedDeque<Packet>> outgoing;
    private final HumanityServer server;

    public OutboundPacketQueue(HumanityServer server) {
        this.server = Preconditions.checkNotNull(server, "server");
        this.outgoing = new HashMap<>();
    }

    @Override
    public void run() {
        while(true) {
            Set<Map.Entry<ClientConnection, ConcurrentLinkedDeque<Packet>>> dequeueCopy = new HashSet<>(outgoing.entrySet());

            for(Map.Entry<ClientConnection, ConcurrentLinkedDeque<Packet>> entry : dequeueCopy) {
                ClientConnection client = entry.getKey();
                ConcurrentLinkedDeque<Packet> packetsCopy = new ConcurrentLinkedDeque<>(entry.getValue());

                for(Packet packet : packetsCopy) {
                    client._sendPacket(packet);
                    outgoing.get(client).remove(packet);
                }
            }
        }
    }

    public void connectClient(ClientConnection client) {
        this.outgoing.put(client, new ConcurrentLinkedDeque<Packet>());
    }

    public void disconnectClient(ClientConnection client) {
        this.outgoing.remove(client);
    }

    public void sendPacket(Packet packet, ClientConnection... clients) {
        this.sendPacket(packet, Arrays.asList(clients));
    }

    public void sendPacket(Packet packet, List<ClientConnection> clients) {
        for (ClientConnection client : clients) {
            this.outgoing.get(client).offer(packet);
        }
    }

    public Queue getQueueFor(ClientConnection client) {
        return outgoing.get(client);
    }

}
