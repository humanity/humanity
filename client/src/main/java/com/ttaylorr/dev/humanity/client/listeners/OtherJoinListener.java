package com.ttaylorr.dev.humanity.client.listeners;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.client.client.MaskedHumanityClient;
import com.ttaylorr.dev.humanity.client.game.ClientGame;
import com.ttaylorr.dev.humanity.server.handlers.Handler;
import com.ttaylorr.dev.humanity.server.handlers.HandlerPriority;
import com.ttaylorr.dev.humanity.server.handlers.Listenable;
import com.ttaylorr.dev.humanity.server.packets.masked.core.Packet09MaskedJoin;
import com.ttaylorr.dev.humanity.server.packets.masked.core.Packet11MaskedDisconnect;

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
