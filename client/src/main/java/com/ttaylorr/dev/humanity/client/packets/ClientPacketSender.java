package com.ttaylorr.dev.humanity.client.packets;

import com.ttaylorr.dev.humanity.server.Client;
import com.ttaylorr.dev.humanity.server.packets.Packet;
import com.ttaylorr.dev.logger.LoggerProvider;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ClientPacketSender implements Runnable {

    private Client server;
    private ConcurrentLinkedDeque<Packet> packets;

    public ClientPacketSender(Client server) {
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

            if (p != null) {
                try {
                    System.out.println("attempting to send packet");
                    sendPacket(p);
                } catch (IOException e) {
                    // TODO error reporting thing.
                    // logError(e);
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized boolean addPacket(Packet p) {
        System.out.println("queuing packet");
        return packets.add(p);
    }

    private void sendPacket(Packet p) throws IOException {
//        server.getOutput().flush();
//        server.getOutput().reset();
//        while(!server.getSocket().isConnected() || !server.getSocket().isOutputShutdown());
//        System.out.println("asdf");
        server.getOutput().writeObject(p);
    }
}
