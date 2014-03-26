package com.ttaylorr.dev.humanity.server.cards.pool;

import com.ttaylorr.dev.humanity.server.cards.card.BlackCard;
import com.ttaylorr.dev.humanity.server.cards.card.WhiteCard;

import java.util.List;

/**
 * TODO add a TrickUpdate Packet
 */
public abstract class ITrick {

    public abstract BlackCard getChoiceCard();

    public abstract List<WhiteCard> getSubmitted();

    public abstract boolean isComplete();

}
