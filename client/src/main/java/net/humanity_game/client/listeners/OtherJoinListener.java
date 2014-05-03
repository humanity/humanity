package net.humanity_game.client.listeners;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.client.client.HumanityClient;
import net.humanity_game.client.game.ClientGame;
import net.humanity_game.client.packets.handler.ClientHandler;
import net.humanity_game.server.handlers.HandlerPriority;
import net.humanity_game.server.handlers.Listenable;
import net.humanity_game.server.packets.masked.core.Packet09UpdatePlayerList;

import java.net.InetSocketAddress;

public class OtherJoinListener implements Listenable {

    private final ClientGame game;

    public OtherJoinListener(ClientGame game) {
        this.game = Preconditions.checkNotNull(game);
    }

    @ClientHandler(
        priority = HandlerPriority.MONITOR,
        handleSelf = false
    )
    public void onMaskedJoin(Packet09UpdatePlayerList packet) {
        ImmutableList<Packet09UpdatePlayerList.PlayerUpdate> players = packet.getUpdatedPlayers();
        for (Packet09UpdatePlayerList.PlayerUpdate player : players) {
            handlePlayer(player);
        }
    }

    private void handlePlayer(Packet09UpdatePlayerList.PlayerUpdate player) {
        StringBuilder builder = new StringBuilder("Other client ");
        if (player.getType() == Packet09UpdatePlayerList.Type.NEW_JOIN) {
            builder.append("joined ");
        } else {
            builder.append("was previously connected ");
        }
        builder.append("with UUID: ");
        builder.append(player.getClientId());

        HumanityClient newClient = new HumanityClient(player.getClientId(), new InetSocketAddress(player.getHost(), player.getPort()));

        this.game.connectPlayer(newClient);
        this.game.getLogger().info(builder.toString());
    }

}
