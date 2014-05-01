package net.humanity_game.client.listeners;

import com.google.common.base.Preconditions;
import net.humanity_game.client.client.MaskedHumanityClient;
import net.humanity_game.client.game.ClientGame;
import net.humanity_game.server.handlers.Handler;
import net.humanity_game.server.handlers.HandlerPriority;
import net.humanity_game.server.handlers.Listenable;
import net.humanity_game.server.packets.masked.core.Packet09MaskedJoin;
import net.humanity_game.server.packets.masked.core.Packet11MaskedDisconnect;

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
        builder.append(packet.getWho().getClientId());

        this.game.connectPlayer(MaskedHumanityClient.fromMaskedClientConnection(packet.getWho()));
        this.game.getLogger().info(builder.toString());
    }

    @Handler(priority = HandlerPriority.MONITOR)
    public void onMaskedDisconnect(Packet11MaskedDisconnect packet) {
        StringBuilder builder = new StringBuilder();
        builder.append("Other client ");
        builder.append("(" + packet.getWho().getName() + ") ");
        builder.append("has disconnected with the UUID: ");
        builder.append(packet.getWho().getClientId());

        this.game.handleLogout(MaskedHumanityClient.fromMaskedClientConnection(packet.getWho()));
        this.game.getLogger().info(builder.toString());
    }
}
