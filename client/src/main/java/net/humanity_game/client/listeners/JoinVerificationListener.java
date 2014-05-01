package net.humanity_game.client.listeners;

import net.humanity_game.client.client.HumanityClient;
import net.humanity_game.server.handlers.Handler;
import net.humanity_game.server.handlers.HandlerPriority;
import net.humanity_game.server.handlers.Listenable;
import net.humanity_game.server.packets.core.Packet04Join;

public class JoinVerificationListener implements Listenable {

    private HumanityClient client;

    public JoinVerificationListener(HumanityClient client) {
        this.client = client;
    }

    @Handler(priority = HandlerPriority.NORMAL)
    public void onJoin(Packet04Join packet) {
        if (packet.getState() == Packet04Join.JoinState.ALLOWED) {
            this.client.getLogger().info("Able to join game with status '{}'", packet.getReason());
            this.client.getLogger().info("Assigned ID (from server): {}", packet.getServerAssignedUUID());
        } else {
            this.client.getLogger().info("Forced to disconnect from the server with status '{}'", packet.getReason());
        }
    }
}
