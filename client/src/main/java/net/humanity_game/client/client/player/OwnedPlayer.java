package net.humanity_game.client.client.player;

import com.google.common.collect.ImmutableList;
import net.humanity_game.client.cards.ClientTrick;
import net.humanity_game.client.client.HumanityClient;
import net.humanity_game.client.game.ClientGame;
import net.humanity_game.server.cards.card.WhiteCard;

import java.util.Collection;

public class OwnedPlayer extends Player {
    public OwnedPlayer(HumanityClient client) {
        super(client.getClientId(), client.getName());
    }

    public void sendCardToTrick(WhiteCard card, ClientGame game) {
        this.sendCardToTrick(ImmutableList.<WhiteCard>builder().add(card).build(), game);
    }

    public void sendCardToTrick(Collection<WhiteCard> cards, ClientGame game) {
        ClientTrick trick = game.getCurrentTrick();
        trick.submitCard(this, cards);
    }
}
