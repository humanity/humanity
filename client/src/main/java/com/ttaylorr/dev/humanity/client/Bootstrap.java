package com.ttaylorr.dev.humanity.client;

public class Bootstrap {

    private static HumanityClient client = new HumanityClient("localhost", 8000);

    public static boolean closeRequested = false;

    public static void main(String[] args) {
        client.getLogger().info("Starting humanity client's bootstrap");
        client.openConnection();

        while (!closeRequested) {

        }
        client.getLogger().info("Closing client bootstrap...");
    }
}
