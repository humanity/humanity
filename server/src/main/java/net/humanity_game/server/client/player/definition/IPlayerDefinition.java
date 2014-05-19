package net.humanity_game.server.client.player.definition;

import net.humanity_game.server.client.player.PlayerState;

public interface IPlayerDefinition {

    public String getName();

    public int getScore();

    public PlayerState getPlayerState();
}
