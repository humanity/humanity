package net.humanity_game.client.client.player;

import net.humanity_game.client.client.HumanityClient;
import net.humanity_game.server.cards.card.WhiteCard;
import net.humanity_game.server.game.HumanityGame;

public class OwnedPlayer extends Player {
    public OwnedPlayer(HumanityClient client) {
        super(client.getClientId(), client.getName());
    }

    public void sendCardToTrick(WhiteCard card, HumanityGame game) {

    }
}
