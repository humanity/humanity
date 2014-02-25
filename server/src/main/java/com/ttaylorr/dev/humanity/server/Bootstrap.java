package com.ttaylorr.dev.humanity.server;

import com.ttaylorr.dev.humanity.server.handlers.core.HandshakeHandler;

import java.io.IOException;

public class Bootstrap {
    static HumanityServer server;

    public static void main(String[] args) {
        try {
            server = new HumanityServer(7000); // make this use Configuration.
            server.getLogger().info("Starting server \"humanity\"...");
            registerHandlers();
            Thread thread = new Thread(server);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private static void registerHandlers() {
        server.registerPacketHandler(new HandshakeHandler());
    }

    public static HumanityServer getServer() {
        return server;
    }

}
