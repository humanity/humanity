package com.ttaylorr.dev.humanity.server.client.definition;

import com.ttaylorr.dev.humanity.server.cards.hand.IHumanityHand;
import com.ttaylorr.dev.humanity.server.client.player.PlayerState;

import java.util.UUID;

public interface IClientDefinition {

    public PlayerState getPlayerState();

    public UUID getUUID();
}
