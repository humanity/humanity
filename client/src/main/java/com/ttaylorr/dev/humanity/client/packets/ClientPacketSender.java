package com.ttaylorr.dev.humanity.client.packets;

import com.ttaylorr.dev.humanity.client.ServerStream;
import com.ttaylorr.dev.humanity.server.packets.Packet;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ClientPacketSender implements Runnable {

    private ServerStream server;
    private ConcurrentLinkedDeque<Packet> packets;

    public ClientPacketSender(ServerStream server) {
        packets = new ConcurrentLinkedDeque<>();
        this.server = server;
    }

    @Override
    public void run() {
        while (true) {
            Packet p = null;
            synchronized (packets) {
                p = packets.pollLast();
            }
            try {
                sendPacket(p);
            } catch (IOException e) {
                // TODO error reporting thing.
                e.printStackTrace();
            }
        }
    }

    public synchronized boolean addPacket(Packet p) {
        return packets.add(p);
    }

    private void sendPacket(Packet p) throws IOException {
        server.output.writeObject(p);
    }

}
