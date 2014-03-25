package com.ttaylorr.dev.humanity.server;

public class Bootstrap {

    static HumanityServer server = new HumanityServer(8000);
    public static final int LOOP_DELAY = 30;

    private static boolean CLOSE_REQUESTED = false;

    public static void main(String[] args) {
        server.open();
        while(!Bootstrap.CLOSE_REQUESTED);
        server.close();
    }

    public static void requestClose() {
        Bootstrap.CLOSE_REQUESTED = true;
    }
}
