package net.humanity_game.client.listeners;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import net.humanity_game.client.Bootstrap;
import net.humanity_game.client.client.HumanityClient;
import net.humanity_game.client.client.player.Player;
import net.humanity_game.client.packets.handler.ClientHandler;
import net.humanity_game.server.handlers.HandlerPriority;
import net.humanity_game.server.handlers.Listenable;
import net.humanity_game.server.packets.core.Packet04Join;

import java.util.Iterator;
import java.util.List;

public class JoinVerificationListener implements Listenable {

    private HumanityClient client;

    public JoinVerificationListener(HumanityClient client) {
        this.client = client;
    }

    @ClientHandler(
        priority = HandlerPriority.NORMAL,
        handleSelf = true
    )
    public void onJoin(Packet04Join packet) {
        if (packet.getState() == Packet04Join.JoinState.ALLOWED) {
            this.client.getLogger().info("Able to join game with status '{}'", packet.getReason());
            this.client.getLogger().info("Assigned ID (from server): {}", packet.getClientId());
            this.client.getDefinition().setUUID(packet.getClientId());

            List<Player> currentPlayers = Bootstrap.getGame().getClientManager().getConnectedClientsMut();

            for (Iterator<Player> it = currentPlayers.iterator(); it.hasNext(); ) {
                Player p = it.next();
                if (p.getClientId().equals(this.client.getClientId())) {
                    it.remove();
                }
            }
        } else {
            this.client.getLogger().info("Forced to disconnect from the server with status '{}'", packet.getReason());
        }
    }
}
