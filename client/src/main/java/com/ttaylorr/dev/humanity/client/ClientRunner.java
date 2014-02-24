package com.ttaylorr.dev.humanity.client;

import com.ttaylorr.dev.humanity.client.packets.ClientPacketSender;
import com.ttaylorr.dev.humanity.server.Client;
import com.ttaylorr.dev.humanity.server.configuration.Configuration;
import com.ttaylorr.dev.humanity.server.configuration.ConfigurationProvider;
import com.ttaylorr.dev.humanity.server.configuration.providers.ClientNormalConfigurationProvider;
import com.ttaylorr.dev.humanity.server.packets.Packet;
import com.ttaylorr.dev.humanity.server.packets.core.Packet03ClientHandshake;

import java.io.IOException;
import java.net.Socket;

public class ClientRunner {

    private ClientPacketSender outputSender;
    //private ServerStream sstream;
    public final Configuration configuration;
    private final Client client;

    public ClientRunner(String ip, int port) throws IOException {
        Socket sock = new Socket(ip, port); // The client is now connected
        // sstream = new ServerStream(sock);
        configuration = new Configuration(new ClientNormalConfigurationProvider());
        client = new Client(sock, configuration.get(ConfigurationProvider.CLIENT_NAME_KEY));
        outputSender = new ClientPacketSender(client);
    }

    public ClientRunner() throws IOException {
        configuration = new Configuration(new ClientNormalConfigurationProvider());
        Socket sock = new Socket(configuration.getServerIp(), configuration.getServerPort());
        //sstream = new ServerStream(sock);
        client = new Client(sock, configuration.get(ConfigurationProvider.CLIENT_NAME_KEY));
        outputSender = new ClientPacketSender(client);
    }

    public void sendPacket(Packet packet) {
        this.getOutputSender().addPacket(packet);
    }

    public static void main(String[] args) throws IOException {
        ClientRunner client = new ClientRunner();

        Thread clientPacketSenderThread = new Thread(client.getOutputSender());
        clientPacketSenderThread.start();

        Packet03ClientHandshake handshake = new Packet03ClientHandshake(client.client, client.configuration.get(ConfigurationProvider.CLIENT_NAME_KEY));

        client.sendPacket(handshake);
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
