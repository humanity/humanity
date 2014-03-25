package com.ttaylorr.dev.humanity.client;

import com.ttaylorr.dev.humanity.server.packets.Packet;

import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;

public class IncomingPacketListener implements Runnable {

    private final HumanityClient client;

    public IncomingPacketListener(final HumanityClient client) {
        this.client = client;
    }

    @Override
    public void run() {
        client.getLogger().info("Ready to listen for packets from the server...");
        while (true) {
            try {
                Object incoming = client.getInputStream().readObject();
                if (incoming instanceof Packet) {
                    Packet packet = (Packet) incoming;
                    this.client.getPacketHandler().handlePacket(packet);
                }
                try {
                    Thread.sleep(Bootstrap.LOOP_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch(SocketException e) {
                Bootstrap.requestClose();
                break;
            } catch(EOFException e) {
                Bootstrap.requestClose();
                break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
