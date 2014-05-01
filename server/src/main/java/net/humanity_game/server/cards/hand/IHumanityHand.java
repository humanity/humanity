package net.humanity_game.server.cards.hand;

import net.humanity_game.server.cards.card.WhiteCard;

import java.util.List;

public interface IHumanityHand {

    public static final int MAX_HAND_SIZE = 7;

    public boolean releaseCard(WhiteCard card);

    public boolean shouldDraw();

    public List<WhiteCard> getCards();
}
