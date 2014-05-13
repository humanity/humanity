package net.humanity_game.server.client.player.definition;

import net.humanity_game.server.client.player.PlayerState;

public interface IPlayerDefinition {

    public String getName();

    public int getScore();

    // public void playCard(WhiteCard card, ITrick forTrick); // todo not sure if this is needed; commented out for now.

    // public void updateHand(); // todo see above

    public PlayerState getPlayerState();
}
