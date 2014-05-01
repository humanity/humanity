package net.humanity_game.server.client.player.definition;

import net.humanity_game.server.cards.card.WhiteCard;
import net.humanity_game.server.cards.pool.ITrick;
import net.humanity_game.server.client.player.PlayerState;

public interface IPlayerDefinition {

    public String getName();

    public int getScore();

    public void playCard(WhiteCard card, ITrick forTrick);

    public void updateHand();

    public PlayerState getPlayerState();

}
