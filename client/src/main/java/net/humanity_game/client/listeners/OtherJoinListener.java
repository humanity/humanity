package net.humanity_game.client.listeners;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.client.client.player.Player;
import net.humanity_game.client.game.ClientGame;
import net.humanity_game.client.packets.handler.ClientHandler;
import net.humanity_game.server.handlers.HandlerPriority;
import net.humanity_game.server.handlers.Listenable;
import net.humanity_game.server.packets.core.Packet09UpdatePlayerList;

import java.util.UUID;

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
            switch (player.getType()) {
                case PREVIOUSLY_CONNECTED:
                case NEW_JOIN:
                    handlePlayer(player);
                    break;
                case REMOVAL:
                    handleDisconnection(player);
                    break;
            }
            handlePlayer(player);
        }
    }

    private void handlePlayer(Packet09UpdatePlayerList.PlayerUpdate player) {
        StringBuilder builder = new StringBuilder("Other client ");
        if (player.getType() == Packet09UpdatePlayerList.Type.NEW_JOIN && this.game.getClientManager().getClientById(player.getClientId()) == null) { // null check to make sure this is a new client.
            builder.append("joined ");
        } else {
            builder.append("was previously connected ");
        }
        builder.append("with UUID: ");
        builder.append(player.getClientId());

        // HumanityClient newClient = new HumanityClient(player.getClientId(), new InetSocketAddress(player.getHost(), player.getPort()));
        Player newPlayer = new Player(player.getClientId(), player.getName());
        this.game.connectPlayer(newPlayer);
        this.game.getLogger().info(builder.toString());
    }

    private void handleDisconnection(Packet09UpdatePlayerList.PlayerUpdate player) {
        handleDisconnection(player.getClientId());
    }

    private void handleDisconnection(UUID uuid) {

        StringBuilder builder = new StringBuilder();

        if (game.getClientManager().getClientById(uuid) == null) {
            builder.append("Other client (").append(uuid.toString()).append(") has been disconnected. This client didn't previously know about this client.");
        } else {
            builder.append("Other client ");
            builder.append("(" + this.game.getClientManager().getClientById(uuid).getName() + ") ");
            builder.append("has disconnected with the UUID: ");
            builder.append(uuid);

            this.game.handleLogout(this.game.getClientManager().getClientById(uuid));
        }
        this.game.getLogger().info(builder.toString());
    }
}
