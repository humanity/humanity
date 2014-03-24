package com.ttaylorr.dev.humanity.server.client.definition;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.HumanityServer;
import com.ttaylorr.dev.humanity.server.cards.BlackCard;
import com.ttaylorr.dev.humanity.server.cards.HumanityHand;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;
import com.ttaylorr.dev.humanity.server.client.state.PlayerState;
import com.ttaylorr.dev.humanity.server.packets.core.Packet05PlayerStateChange;

/**
 * The server's perception on what the current state of the client is.
 *
 * Fallback to the definitions given in this class.
 */
public class ServerClientDefinition implements IClientDefinition {

    private PlayerState state;
    private final ClientConnection owner;
    private final HumanityServer server;
    private final HumanityHand hand;
    private int score;

    public ServerClientDefinition(ClientConnection owner, HumanityServer server) {
        this.owner = Preconditions.checkNotNull(owner, "client connection");
        this.server = Preconditions.checkNotNull(server, "server");
        this.hand = new HumanityHand(owner);
    }

    @Override
    public PlayerState getPlayerState() {
        return this.state;
    }

    public PlayerState setPlayerState(PlayerState newPlayerState) {
        this.state = newPlayerState;
        this.owner.sendPacket(new Packet05PlayerStateChange(newPlayerState));
        return this.state;
    }

    @Override
    public HumanityHand getPlayerHand() {
        return null;
    }

    @Override
    public int getScore() {
        return score;
    }

    public void incrementScore(BlackCard card) {
        this.score++;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
