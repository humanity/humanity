package com.ttaylorr.dev.humanity.server;

import java.io.IOException;

public class Bootstrap {
    static HumanityServer server;

    public static void main(String[] args) {
        try {
            server = new HumanityServer(7002);
            server.getLogger().info("Starting server \"humanity\"...");
            Thread thread = new Thread(server);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static HumanityServer getServer() {
        return server;
    }

}
