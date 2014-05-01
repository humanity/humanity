package net.humanity_game.client.client.player;

import net.humanity_game.client.Bootstrap;
import net.humanity_game.client.cards.ClientHumanityHand;
import net.humanity_game.server.cards.card.WhiteCard;
import net.humanity_game.server.cards.pool.ITrick;
import net.humanity_game.server.client.player.PlayerState;

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
