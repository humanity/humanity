package com.ttaylorr.dev.humanity.client.client.player;

import com.ttaylorr.dev.humanity.server.cards.card.WhiteCard;
import com.ttaylorr.dev.humanity.server.cards.pool.ITrick;

/*public*/
class ObservedClientPlayerDefinition extends ClientPlayerDefinition {


    public ObservedClientPlayerDefinition(String name) {
        super(name);
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void playCard(WhiteCard card, ITrick forTrick) {
        throw new UnsupportedOperationException("Can't play a card for this player");
    }

    @Override
    public void updateHand() {
        throw new UnsupportedOperationException("Can't update this player's hand");
    }


}
