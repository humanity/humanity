package com.ttaylorr.dev.humanity.server;

public class Bootstrap {

    static HumanityServer server = new HumanityServer(8000);

    public static void main(String[] args) {
        server.open();
        while(true);
    }
}
