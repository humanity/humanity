package com.ttaylorr.dev.humanity.client;

import com.ttaylorr.dev.humanity.client.packets.ClientPacketSender;
import com.ttaylorr.dev.humanity.server.Client;
import com.ttaylorr.dev.humanity.server.packets.Packet;
import com.ttaylorr.dev.humanity.server.packets.core.Packet03ClientHandshake;

import java.io.IOException;

public class ClientRunner {

    private ClientPacketSender outputSender;
    private Client client;

    public ClientRunner() {
        try {
            this.client = new Client("localhost", 7000, false);
            this.outputSender = new ClientPacketSender(this.getClient());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendPacket(ClientPacketSender sender, Packet packet) {
        sender.addPacket(packet);
    }

    public static void main(String[] args) throws IOException {
        ClientRunner client = new ClientRunner();

        Thread clientPacketSenderThread = new Thread(client.getOutputSender());
        clientPacketSenderThread.start();

        Packet03ClientHandshake handshake = new Packet03ClientHandshake(client.client, "Client");

        sendPacket(client.outputSender, handshake);
        while(true){}
        // run the Client backend.
        // perhaps the GUI will have its own main() that would call this?
    }

    public ClientPacketSender getOutputSender() {
        return outputSender;
    }

    public Client getClient() {
        return client;
    }

}
