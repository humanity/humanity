package com.ttaylorr.dev.humanity.client.packets;

import com.ttaylorr.dev.humanity.client.ServerStream;
import com.ttaylorr.dev.humanity.server.packets.Packet;

import java.io.IOException;
import java.util.LinkedList;

public class ClientPacketSender implements Runnable {

    private ServerStream server;
    private LinkedList<Packet> packets;

    public ClientPacketSender(ServerStream server) {
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

    public synchronized void addPacket(Packet p) {
        packets.add(p);
    }

    private void sendPacket(Packet p) throws IOException {
        server.output.writeObject(p);
    }

}
