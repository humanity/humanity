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
    private PlayerState state;
    private UUID clientUUID;

    public ClientDefinition(UUID uuid, HumanityClient client) {
        this.client = Preconditions.checkNotNull(client, "client");
        this.client.getPacketHandler().registerHandlers(this);
        this.clientUUID = uuid;
        state = null;
    }

    public ClientDefinition(UUID uuid, HumanityClient client, PlayerState state) {
        this(uuid, client);
        this.state = state;
    }

    @Override
    public PlayerState getPlayerState() {
        return state;
    }

    public void setPlayerState(PlayerState state) {
        this.state = Preconditions.checkNotNull(state, "player state");
    }

    @ClientHandler(priority = HandlerPriority.MONITOR, handleSelf = true)
    public void onPlayerStateChange(Packet05PlayerStateChange packet) {
        setPlayerState(packet.getTo());
    }

    public UUID getUUID() {
        return clientUUID;
    }

    @Override
    public IHumanityHand getPlayerHand() {
        return null;
    }

    public void setUUID(UUID uuid) {
        Preconditions.checkState(this.clientUUID == null, "client id must be null");
        this.clientUUID = uuid;
    }
}
