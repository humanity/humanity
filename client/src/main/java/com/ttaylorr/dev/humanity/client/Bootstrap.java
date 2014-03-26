package com.ttaylorr.dev.humanity.client;

import com.ttaylorr.dev.humanity.client.client.HumanityClient;
import com.ttaylorr.dev.humanity.client.game.ClientGame;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Bootstrap {

    private static HumanityClient client;
    private static ClientGame game;
    public static ScheduledThreadPoolExecutor threadPoolExecutor;
    public static final int LOOP_DELAY = 50;

    public static boolean closeRequested = false;

    public static void main(String[] args) throws InterruptedException {
        client = new HumanityClient("localhost", 8000);
        client.getLogger().info("Starting humanity client's bootstrap");

        threadPoolExecutor = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors());
        client.getLogger().info("Opened thread pool...");

        game = new ClientGame(client);
        game.getLogger().info("Created the game, now listening for updates...");

        client.getLogger().info("Deferred process to opening connection!");
        client.openConnection();

        while (!closeRequested) {
            // So, you know, we don't murder CPUs.
            Thread.sleep(Bootstrap.LOOP_DELAY);
        }
        client.getLogger().info("Closing client bootstrap...");
    }

    public static void requestClose() {
        Bootstrap.closeRequested = true;
    }

    public static HumanityClient getClient() {
        return Bootstrap.client;
    }
}
