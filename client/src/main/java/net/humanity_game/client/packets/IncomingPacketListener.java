package net.humanity_game.client.packets;

import net.humanity_game.client.Bootstrap;
import net.humanity_game.client.client.HumanityClient;
import net.humanity_game.core.packets.AbstractIncomingPacketListener;
import net.humanity_game.server.packets.Packet;

import java.io.IOException;

public class IncomingPacketListener extends AbstractIncomingPacketListener {

    private final HumanityClient client;

    public IncomingPacketListener(final HumanityClient client) {
        this.client = client;
    }

    @Override
    public void run() {
        this.client.getLogger().info("Ready to listen for packets from the server...");
        while (true) {
            if (this.isCloseRequested()) {
                return;
            } else if (this.isDataPresent()) {
                Object obj = this.readObject();

                if (obj instanceof Packet) {
                    this.client.getPacketHandler().handlePacket((Packet) obj);
                }
            } else {
                this.yield();
            }
        }
    }

    @Override
    protected Object readObject() {
        try {
            return this.client.getInputStream().readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            this.client.getLogger().warn("Unrecoverable error! The server dropped its connection.");
            Bootstrap.requestClose();
            System.exit(-1);
        }
        return null;
    }

    @Override
    protected boolean isDataPresent() {
        try {
            int available = this.client.getInputStream().available();
            return available == 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
