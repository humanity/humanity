package net.humanity_game.server.client.definition;

import net.humanity_game.server.cards.hand.IHumanityHand;
import net.humanity_game.server.client.player.PlayerState;

import java.util.UUID;

public interface IClientDefinition {

    public PlayerState getPlayerState();

    public UUID getUUID();

    public IHumanityHand getPlayerHand();
}
