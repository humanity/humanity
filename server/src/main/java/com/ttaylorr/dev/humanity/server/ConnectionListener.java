package com.ttaylorr.dev.humanity.server;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;

import java.io.IOException;
import java.net.Socket;

public class ConnectionListener implements Runnable {

    private HumanityServer server;

    public ConnectionListener(HumanityServer server) {
        Preconditions.checkArgument(server.isOpen(), "server is closed");
        this.server = Preconditions.checkNotNull(server, "server");
    }

    @Override
    public void run() {
        server.getLogger().info("Now listening for clients...");
        while (true) {
            try {
                Socket clientSocket = server.getSocket().accept();

                if (clientSocket != null) {
                    ClientConnection client = new ClientConnection(clientSocket);
                    this.server.getClientManager().connectClient(client);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
