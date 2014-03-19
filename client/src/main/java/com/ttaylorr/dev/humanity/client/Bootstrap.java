package com.ttaylorr.dev.humanity.client;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Bootstrap {

    private static HumanityClient client = new HumanityClient("localhost", 8000);
    public static ScheduledThreadPoolExecutor threadPoolExecutor;

    public static boolean closeRequested = false;

    public static void main(String[] args) {
        client.getLogger().info("Starting humanity client's bootstrap");

        threadPoolExecutor = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors());
        client.getLogger().info("Opened thread pool...");

        client.getLogger().info("Deferred process to opening connection!");
        client.openConnection();

        while (!closeRequested);
        client.getLogger().info("Closing client bootstrap...");
    }

    public static void requestClose() {
        Bootstrap.closeRequested = true;
    }
}
