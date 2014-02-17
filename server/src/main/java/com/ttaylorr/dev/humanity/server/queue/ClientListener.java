package com.ttaylorr.dev.humanity.server.queue;

import com.ttaylorr.dev.humanity.server.Client;
import com.ttaylorr.dev.humanity.server.packets.Packet;
import com.ttaylorr.dev.humanity.server.packets.SimplePacketManager;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Read the inbound Packets from a given client.
 * This class must be run on another Thread, and that Thread must be daemon,
 * because there is no need to have this running after the server shuts down.
 * User: Jack
 * Date: 2/11/14
 * Time: 9:57 AM
 */
public class ClientListener implements Runnable {
    private final SimplePacketManager packetManager;
    private final Client client;


    public ClientListener(SimplePacketManager packetManager, Client client) {
        this.packetManager = packetManager;
        this.client = client;
    }


    @Override
    public void run() {
        ObjectInputStream input = client.getInput();
        while (true) {
            try {
                Object obj = input.readObject();
                if (obj instanceof Packet) {
                    Packet p = (Packet) obj;
                    p.setOwner(client); // makes sure that a Client can't trick the server by telling us it's not from the Client we think it's from.
                    this.packetManager.queuePacket(p);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
