package com.ttaylorr.dev.humanity.client.listeners;

import com.ttaylorr.dev.humanity.client.HumanityClient;
import com.ttaylorr.dev.humanity.server.handlers.Handler;
import com.ttaylorr.dev.humanity.server.handlers.HandlerPriority;
import com.ttaylorr.dev.humanity.server.handlers.Listenable;
import com.ttaylorr.dev.humanity.server.packets.core.Packet04Join;

public class JoinVerificationListener implements Listenable {

    private HumanityClient client;

    public JoinVerificationListener(HumanityClient client) {
        this.client = client;
    }

    @Handler(priority = HandlerPriority.NORMAL)
    public void onJoin(Packet04Join packet) {
        if (packet.getState() == Packet04Join.JoinState.ALLOWED) {
            this.client.getLogger().info("Able to join game with status '{}'", packet.getReason());
            this.client.getLogger().info("Assigned UUID (from server): {}", packet.getServerAssignedUUID());
        } else {
            this.client.getLogger().info("Forced to disconnect from the server with status '{}'", packet.getReason());
        }
    }
}
