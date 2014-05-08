package net.humanity_game.client.definition;

import com.google.common.base.Preconditions;
import net.humanity_game.client.client.HumanityClient;
import net.humanity_game.client.packets.handler.ClientHandler;
import net.humanity_game.server.cards.hand.IHumanityHand;
import net.humanity_game.server.client.definition.IClientDefinition;
import net.humanity_game.server.client.player.PlayerState;
import net.humanity_game.server.handlers.HandlerPriority;
import net.humanity_game.server.handlers.Listenable;
import net.humanity_game.server.packets.core.Packet05PlayerStateChange;

import java.util.UUID;

public class ClientDefinition implements IClientDefinition, Listenable {

    private final HumanityClient client;

    private UUID clientUUID;

    public ClientDefinition(UUID uuid, HumanityClient client) {
        this.client = Preconditions.checkNotNull(client, "client");
        this.client.getPacketHandler().registerHandlers(this);
        this.clientUUID = uuid;
        // TODO construct initial OwnedPlayer here.
    }

    @Override
    public PlayerState getPlayerState() {
        // return this.player.getPlayerState();
        return null;
    }

    @ClientHandler(priority = HandlerPriority.MONITOR, handleSelf = true)
    public void onPlayerStateChange(Packet05PlayerStateChange packet) {
        // this.player.setPlayerState(packet.getTo());
    }

    public UUID getUUID() {
        return clientUUID;
    }

    @Override
    public IHumanityHand getPlayerHand() {
        return null;
    }

    public void setUUID(UUID uuid) {
        if (this.clientUUID == null) {
            this.clientUUID = uuid;
        } else {
            throw new IllegalArgumentException("You may not reassign a client's UUID");
        }
    }
}
