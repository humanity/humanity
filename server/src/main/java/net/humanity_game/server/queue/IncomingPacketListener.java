package net.humanity_game.server.queue;

import net.humanity_game.core.packets.AbstractIncomingPacketListener;
import net.humanity_game.server.HumanityServer;
import net.humanity_game.server.client.ClientConnection;
import net.humanity_game.server.packets.Packet;
import net.humanity_game.server.packets.PacketSnapshot;

import java.io.IOException;

/**
 * Packet listener, specific per client's connect.
 */
public class IncomingPacketListener extends AbstractIncomingPacketListener {

    private final ClientConnection client;
    private final HumanityServer server;

    public IncomingPacketListener(final ClientConnection client, final HumanityServer server) {
        this.client = client;
        this.server = server;
    }

    @Override
    public void run() {
        while (true) {
            if (this.isCloseRequested()) {
                return;
            } else if (this.isDataPresent()) {
                // Check if there is any data to read i.n before doing so
                Object obj = this.readObject();

                if (obj instanceof Packet) {
                    Packet packet = (Packet) obj;
                    PacketSnapshot snapshot = new PacketSnapshot(packet, this.client);
                    this.server.getPacketManager().handlePacket(snapshot);
                }
            } else {
                this.yield();
            }
        }
    }

    protected Object readObject() {
        try {
            Object obj = this.client.getInput().readObject();
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected boolean isDataPresent() {
        try {
            // no bytes can be read without blocking, meaning there is data to be read
            int available = this.client.getInput().available();
            return available == 0;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
