package com.ttaylorr.dev.humanity.server.client.definition;

import com.ttaylorr.dev.humanity.server.cards.hand.IHumanityHand;
import com.ttaylorr.dev.humanity.server.client.player.PlayerState;

public interface IClientDefinition {

    public PlayerState getPlayerState();

    public IHumanityHand getPlayerHand();

    public int getScore();
}
