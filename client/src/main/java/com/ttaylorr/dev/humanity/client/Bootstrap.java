package com.ttaylorr.dev.humanity.client;

public class Bootstrap {


    private static HumanityClient client = new HumanityClient("localhost", 7000);

    public static boolean closeRequested = false;

    public static void main(String[] args) {
        client.openConnection();

        while (!closeRequested) {

        }
    }
}
