package com.ttaylorr.dev.humanity.server.cards.hand;

import com.ttaylorr.dev.humanity.server.cards.card.WhiteCard;

import java.util.List;

public interface IHumanityHand {

    public static final int MAX_HAND_SIZE = 7;

    public boolean releaseCard(WhiteCard card);

    public boolean addCard(WhiteCard card);

    public boolean shouldDraw();

    public List<WhiteCard> getCards();
}
