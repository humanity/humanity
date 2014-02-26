package com.ttaylorr.dev.humanity.server;

public class Bootstrap {

    static HumanityServer server = new HumanityServer(7000);

    public static void main(String[] args) {
        server.open();
        while(true);
    }
}
