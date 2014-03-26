package com.ttaylorr.dev.humanity.server.client.player.definition;

import com.ttaylorr.dev.humanity.server.cards.card.WhiteCard;
import com.ttaylorr.dev.humanity.server.cards.pool.ITrick;
import com.ttaylorr.dev.humanity.server.client.player.PlayerState;

public interface IPlayerDefinition {

    public String getName();

    public int getScore();

    public void playCard(WhiteCard card, ITrick forTrick);

    public void updateHand();

    public PlayerState getPlayerState();

}
