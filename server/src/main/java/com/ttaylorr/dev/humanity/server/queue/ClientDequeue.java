package com.ttaylorr.dev.humanity.server.queue;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.HumanityServer;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;
import com.ttaylorr.dev.humanity.server.packets.Packet;
import com.ttaylorr.dev.logger.Logger;
import com.ttaylorr.dev.logger.LoggerProvider;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ClientDequeue implements Runnable {

    private ConcurrentLinkedDeque<Packet> packets;
    private final ClientConnection connection;
    private final HumanityServer server;
    private final Logger logger;

    public ClientDequeue(ClientConnection connection, HumanityServer server) {
        this.connection = Preconditions.checkNotNull(connection, "connection");
        this.server = Preconditions.checkNotNull(server, "server");
        this.logger = LoggerProvider.putLogger(this.getClass());
        this.packets = new ConcurrentLinkedDeque<>();
    }

    @Override
    public void run() {
        while(true) {
            for(Packet packet : new ConcurrentLinkedDeque<>(this.packets)) {
                try {
                    this.logger.debug("[INTERNAL] (S->C) sent: {}", packet.getClass().getSimpleName());
                    this.connection.getOutput().writeObject(packet);
                    this.connection.getOutput().reset();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.packets.remove(packet);
            }
        }
    }

    public void sendPacket(Packet packet) {
        this.packets.offer(packet);
    }
}
