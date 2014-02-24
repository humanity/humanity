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
            try {
                sendPacket(p);
            } catch (IOException e) {
                // TODO error reporting thing.
                logError(e);
            }
        }
    }

    public static void logError(Exception e) {
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        for (String s : writer.toString().split("\n")) {
            LoggerProvider.putLogger(ClientPacketSender.class).severe(s);
        }
    }

    public synchronized boolean addPacket(Packet p) {
        return packets.add(p);
    }

    private void sendPacket(Packet p) throws IOException {
        server.getOutput().writeObject(p);
    }

}
