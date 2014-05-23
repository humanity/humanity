package net.humanity_game.server.client.definition;

import com.google.common.base.Preconditions;
import net.humanity_game.server.HumanityServer;
import net.humanity_game.server.cards.card.WhiteCard;
import net.humanity_game.server.cards.hand.IHumanityHand;
import net.humanity_game.server.cards.hand.ServerHumanityHand;
import net.humanity_game.server.client.ClientConnection;
import net.humanity_game.server.client.player.PlayerState;
import net.humanity_game.server.packets.core.Packet05PlayerStateChange;
import net.humanity_game.server.packets.core.Packet06HandUpdate;

import java.util.List;
import java.util.UUID;

/**
 * The server's perception on what the current state of the client is.
 *
 * Fallback to the definitions given in this class.
 */
public class ServerClientDefinition implements IClientDefinition {

    private PlayerState state;
    private final ClientConnection owner;
    private final HumanityServer server;
    private final ServerHumanityHand hand;
    private int score;

    public ServerClientDefinition(ClientConnection owner, HumanityServer server) {
        this.owner = Preconditions.checkNotNull(owner, "client connection");
        this.server = Preconditions.checkNotNull(server, "server");
        this.hand = new ServerHumanityHand(owner);
        score = 0;
    }

    @Override
    public PlayerState getPlayerState() {
        return this.state;
    }

    @Override
    public UUID getUUID() {
        return this.owner.getClientId();
    }

    public PlayerState setPlayerState(PlayerState newPlayerState) {
        this.state = newPlayerState;
        this.owner.sendPacket(new Packet05PlayerStateChange(this.getUUID(), newPlayerState));
        return this.state;
    }

    @Override
    public ServerHumanityHand getPlayerHand() {
        return this.hand;
    }

    @Override
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void updatePlayerHand(IHumanityHand hand) {
        this.updatePlayerHand(hand.getCards());
    }

    public void updatePlayerHand(List<WhiteCard> newCards) {
        Packet06HandUpdate packet = new Packet06HandUpdate(this.getUUID(), newCards, null);
        this.owner.sendPacket(packet);
    }


}
