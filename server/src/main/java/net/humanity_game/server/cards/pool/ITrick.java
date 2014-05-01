package net.humanity_game.server.cards.pool;

import net.humanity_game.server.cards.card.BlackCard;
import net.humanity_game.server.cards.card.WhiteCard;

import java.util.List;

/**
 * TODO add a TrickUpdate Packet
 */
public abstract class ITrick {

    public abstract BlackCard getChoiceCard();

    public abstract List<WhiteCard> getSubmitted();

    public abstract boolean isComplete();

}
