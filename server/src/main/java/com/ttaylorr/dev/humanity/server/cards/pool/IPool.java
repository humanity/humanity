package com.ttaylorr.dev.humanity.server.cards.pool;

import com.ttaylorr.dev.humanity.server.cards.card.BlackCard;
import com.ttaylorr.dev.humanity.server.cards.card.WhiteCard;

import java.util.List;

public interface IPool {

    public BlackCard getChoiceCard();

    public List<WhiteCard> getSubmitted();

    public boolean isComplete();

}
