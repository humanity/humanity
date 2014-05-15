package net.humanity_game.client.client;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.client.Bootstrap;
import net.humanity_game.client.client.player.Player;
import net.humanity_game.client.packets.handler.ClientHandler;
import net.humanity_game.server.client.IClientManager;
import net.humanity_game.server.handlers.HandlerPriority;
import net.humanity_game.server.handlers.Listenable;
import net.humanity_game.server.packets.core.Packet04Join;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class ClientManager implements IClientManager<Player>, Listenable {

    private List<Player> clients;

    public ClientManager() {
        this.clients = new ArrayList<>();
    }

    public void connectClient(Player player) {
        Preconditions.checkNotNull(player, "player");
        Preconditions.checkNotNull(player.getClientId(), "player uuid");

        this.clients.add(player);
    }

    public void disconnectClient(Player player) {
        this.clients.remove(player);
    }

    public Player getClientById(UUID id) {
        if (id == null) {
            return null;
        } else {
            for (Player client : this.clients) {
                Preconditions.checkNotNull(client.getClientId(), "tmp: client uuid null");
                if (client.getClientId().equals(id)) {
                    return client;
                }
            }
            return null;
        }
    }

    public UUID getUUIDForClient(Player humanityClient) {
        for (Player client : this.clients) {
            if (client.equals(humanityClient)) {
                return client.getClientId();
            }
        }
        return null;
    }

    public ImmutableList<Player> getConnectedClients() {
        return ImmutableList.copyOf(this.clients);
    }

    @ClientHandler(priority = HandlerPriority.MONITOR, handleSelf = true)
    public void pruneOnJoin(Packet04Join packet04Join) {
        for (Iterator<Player> it = this.clients.iterator(); it.hasNext(); ) {
            if (it.next().getClientId().equals(Bootstrap.getClient().getClientId())) {
                it.remove();
            }
        }
    }

    /**
     * Never prefer this method--it is for use only by JoinVerificationListener
     * @return
     */
    @Deprecated
    public List<Player> getConnectedClientsMut() {
        return this.clients;
    }
}
