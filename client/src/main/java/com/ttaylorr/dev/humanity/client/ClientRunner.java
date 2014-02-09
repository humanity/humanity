package com.ttaylorr.dev.humanity.client;

import com.ttaylorr.dev.humanity.client.packets.ClientPacketSender;
import com.ttaylorr.dev.humanity.server.configuration.Configuration;
import com.ttaylorr.dev.humanity.server.configuration.providers.ClientNormalConfigurationProvider;

import java.io.IOException;
import java.net.Socket;

public class ClientRunner {

    private ClientPacketSender outputSender;
    private ServerStream sstream;
    public final Configuration configuration;

    ClientRunner(String ip, int port) throws IOException {
        Socket sock = new Socket(ip, port);
        sstream = new ServerStream(sock);
        outputSender = new ClientPacketSender(sstream);
        configuration = new Configuration(new ClientNormalConfigurationProvider());
    }

    public static void main(String[] args) {
        // run the Client backend.
        // perhaps the GUI will have its own main() that would call this?
    }

    public ClientPacketSender getOutputSender() {
        return outputSender;
    }

    public ServerStream getSstream() {
        return sstream;
    }

}
