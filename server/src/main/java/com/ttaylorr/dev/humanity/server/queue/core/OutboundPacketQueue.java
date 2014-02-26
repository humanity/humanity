package com.ttaylorr.dev.humanity.server.queue.core;

import com.ttaylorr.dev.humanity.server.client.ClientConnection;
import com.ttaylorr.dev.humanity.server.packets.Packet;
import com.ttaylorr.dev.humanity.server.queue.Queuable;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class OutboundPacketQueue implements Queuable {

    private final Map<ClientConnection, ConcurrentLinkedDeque<Packet>> outgoing;

    public OutboundPacketQueue() {
        this.outgoing = new HashMap<>();
    }

    public void addPacket(Packet packet, ClientConnection... clients) {
        this.addPacket(packet, Arrays.asList(clients));
    }

    public void addPacket(Packet packet, List<ClientConnection> clients) {
        for (ClientConnection client : clients) {
            this.outgoing.get(client).offer(packet);
        }
    }

    @Override
    public Queue getQueue() {
        ConcurrentLinkedDeque<Packet> queues = new ConcurrentLinkedDeque<>();
        for (ConcurrentLinkedDeque<Packet> outgoing : this.outgoing.values()) {
            queues.addAll(outgoing);
        }
        return queues;
    }

    public Queue getQueueFor(ClientConnection client) {
        return outgoing.get(client);
    }

    public Queue getQueueOf(Class<? extends Packet> type) {
        ConcurrentLinkedDeque<Packet> packets = new ConcurrentLinkedDeque<>();
        for (ConcurrentLinkedDeque<Packet> outgoing : this.outgoing.values()) {
            for (Packet packet : outgoing) {
                if (packet.getClass() == type) {
                    packets.add(packet);
                }
            }
        }

        return packets;
    }

    @Override
    public void handleFirst() {

    }
}
