package com.ttaylorr.dev.humanity.server.queue.core;

import com.ttaylorr.dev.humanity.server.HumanityServer;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;
import com.ttaylorr.dev.humanity.server.packets.Packet;
import com.ttaylorr.dev.humanity.server.packets.PacketSnapshot;

import java.io.IOException;
import java.net.SocketException;

public class IncomingPacketListener implements Runnable {

    private final ClientConnection client;
    private final HumanityServer server;

    public IncomingPacketListener(final ClientConnection client, final HumanityServer server) {
        this.client = client;
        this.server = server;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object incoming = client.getInput().readObject();
                if (incoming instanceof Packet) {
                    Packet packet = (Packet) incoming;
                    PacketSnapshot snapshot = new PacketSnapshot(packet, this.client);
                    this.server.getPacketManager().handlePacket(snapshot);
                }
            } catch(SocketException e) {
                // The client closed
                this.server.disconnectClient(this.client);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}