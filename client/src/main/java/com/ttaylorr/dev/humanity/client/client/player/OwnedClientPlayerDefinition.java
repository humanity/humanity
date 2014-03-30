package com.ttaylorr.dev.humanity.client.client.player;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.client.Bootstrap;
import com.ttaylorr.dev.humanity.client.cards.ClientHumanityHand;
import com.ttaylorr.dev.humanity.server.cards.card.WhiteCard;
import com.ttaylorr.dev.humanity.server.cards.pool.ITrick;
import com.ttaylorr.dev.humanity.server.client.player.PlayerState;

public class OwnedClientPlayerDefinition extends ClientPlayerDefinition {

    private ClientHumanityHand playerHand;

    protected OwnedClientPlayerDefinition(String name) {
        super(name);
        this.playerHand = new ClientHumanityHand(Bootstrap.getClient());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getScore() {
        return score;
    }

    public void setPlayerState(PlayerState state) {
        this.state = state;
    }

    public ClientHumanityHand getHand() {
        return this.playerHand;
    }

//    public void handleHandUpdate(Packet12HandUpdate packet) {
//        Preconditions.checkNotNull(packet, "packet");
//        Preconditions.checkNotNull(this.playerHand);
//
//
//    }

    @Override
    public void playCard(WhiteCard card, ITrick forTrick) {
        // TODO implement game logic
    }

    @Override
    public void updateHand() {
        // TODO implement game logic
    }

}
