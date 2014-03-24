package com.ttaylorr.dev.humanity.client;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Bootstrap {

    private static HumanityClient client = new HumanityClient("localhost", 8000);
    public static ScheduledThreadPoolExecutor threadPoolExecutor;
    public static final int LOOP_DELAY = 50;

    public static boolean closeRequested = false;

    public static void main(String[] args) throws InterruptedException {
        client.getLogger().info("Starting humanity client's bootstrap");

        threadPoolExecutor = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors());
        client.getLogger().info("Opened thread pool...");

        client.getLogger().info("Deferred process to opening connection!");
        client.openConnection();

        while (!closeRequested) {
            // So, you know - we don't murder CPUs.
            Thread.sleep(Bootstrap.LOOP_DELAY);
        }
        client.getLogger().info("Closing client bootstrap...");
    }

    public static void requestClose() {
        Bootstrap.closeRequested = true;
    }
}
