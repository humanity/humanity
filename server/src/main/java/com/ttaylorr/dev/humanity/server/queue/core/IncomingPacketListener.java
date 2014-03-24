package com.ttaylorr.dev.humanity.server.queue.core;

import com.ttaylorr.dev.humanity.server.Bootstrap;
import com.ttaylorr.dev.humanity.server.HumanityServer;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;
import com.ttaylorr.dev.humanity.server.packets.Packet;
import com.ttaylorr.dev.humanity.server.packets.PacketSnapshot;

import java.io.IOException;
import java.net.SocketException;

/**
 * Packet listener, specific per client's connect.
 */
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
                this.server.getClientManager().disconnectClient(this.client);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(Bootstrap.LOOP_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
