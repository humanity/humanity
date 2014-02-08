package com.ttaylorr.dev.humanity.server.queue;

import com.ttaylorr.dev.humanity.server.Client;
import com.ttaylorr.dev.humanity.server.packets.Packet;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;

public class PacketQueueRunnable implements Runnable {

    private ConcurrentLinkedDeque<Map.Entry<Packet, Client>> packets;

    public PacketQueueRunnable() {
        packets = new ConcurrentLinkedDeque<>();
    }

    @Override
    public void run() {
        while (true) {
            Map.Entry<Packet, Client> entry;
            synchronized (packets) {
                entry = packets.pollLast();
            }

            if (entry == null) {
                continue;
            }
            try {
                sendPacket(entry.getKey(), entry.getValue());
            } catch (IOException e) {
                // again, error thang.
                e.printStackTrace();
            }
        }
    }

    private void sendPacket(Packet packet, Client client) throws IOException {
        client.getOutput().writeObject(packet); // must be synchronous.
    }

    public synchronized void addPacket(Packet packet, Client client) {
        packets.add(new AbstractMap.SimpleImmutableEntry<Packet, Client>(packet, client));
    }

    public synchronized void addPackets(Iterable<Client> clients, Packet packet) {
        for (Client client : clients) {
            this.addPacket(packet, client);
        }
    }
}
