package net.humanity_game.client.listeners;

import com.google.common.base.Preconditions;
import net.humanity_game.client.client.HumanityClient;
import net.humanity_game.client.game.ClientGame;
import net.humanity_game.server.handlers.Handler;
import net.humanity_game.server.handlers.HandlerPriority;
import net.humanity_game.server.handlers.Listenable;
import net.humanity_game.server.packets.masked.core.Packet09MaskedJoin;
import net.humanity_game.server.packets.masked.core.Packet11MaskedDisconnect;

import java.net.InetSocketAddress;

public class OtherJoinListener implements Listenable {

    private final ClientGame game;

    public OtherJoinListener(ClientGame game) {
        this.game = Preconditions.checkNotNull(game);
    }

    @Handler(priority = HandlerPriority.MONITOR)
    public void onMaskedJoin(Packet09MaskedJoin packet) {
        StringBuilder builder = new StringBuilder("Other client ");
        if (packet.getType() == Packet09MaskedJoin.Type.NEW_JOIN) {
            builder.append("joined ");
        } else {
            builder.append("was previously connected ");
        }
        builder.append("with UUID: ");
        builder.append(packet.getTarget());

        HumanityClient newClient = new HumanityClient(packet.getTarget(), new InetSocketAddress(packet.getHost(), packet.getPort()));

        this.game.connectPlayer(newClient);
        this.game.getLogger().info(builder.toString());
    }

    @Handler(priority = HandlerPriority.MONITOR)
    public void onMaskedDisconnect(Packet11MaskedDisconnect packet) {
        StringBuilder builder = new StringBuilder();

        if (game.getClientManager().getClientById(packet.getTarget()) == null) {
            builder.append("Other client (").append(packet.getTarget().toString()).append(") has been disconnected. This client didn't previously know about this client.");
        } else {
            builder.append("Other client ");
            builder.append("(" + this.game.getClientManager().getClientById(packet.getTarget()).getName() + ") ");
            builder.append("has disconnected with the UUID: ");
            builder.append(packet.getTarget());

            this.game.handleLogout(this.game.getClientManager().getClientById(packet.getTarget()));
        }
        this.game.getLogger().info(builder.toString());

    }
}
