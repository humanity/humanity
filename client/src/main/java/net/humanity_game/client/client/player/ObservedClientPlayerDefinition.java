package net.humanity_game.client.client.player;

import net.humanity_game.server.cards.card.WhiteCard;
import net.humanity_game.server.cards.pool.ITrick;

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
