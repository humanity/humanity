package net.humanity_game.server;

import java.net.BindException;

public class Bootstrap {

    static HumanityServer server = new HumanityServer(8000);
    public static final int LOOP_DELAY = 30;

    private static boolean CLOSE_REQUESTED = false;

    public static void main(String[] args) {
        try {
            server.open();
        } catch (BindException e) {
            server.getLogger().severe("Could not bind to port, shutting down!");
            requestClose();
        }
        while (!Bootstrap.CLOSE_REQUESTED) ;
        server.close();
    }

    public static void requestClose() {
        Bootstrap.CLOSE_REQUESTED = true;
    }
}
