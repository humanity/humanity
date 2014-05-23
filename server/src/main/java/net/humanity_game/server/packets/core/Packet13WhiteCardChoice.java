package net.humanity_game.server.packets.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import net.humanity_game.server.cards.card.WhiteCard;
import net.humanity_game.server.cards.pool.ITrick;
import net.humanity_game.server.packets.Packet;

import java.util.Collection;
import java.util.Collections;

/**
 * C->S
 */
public class Packet13WhiteCardChoice extends Packet {

    private final ITrick trick;
    private final Collection<WhiteCard> cards;

    public Packet13WhiteCardChoice(ITrick destTrick, Collection<WhiteCard> whiteCards) {
        super(null);
        this.trick = Preconditions.checkNotNull(destTrick, "trick");
        whiteCards.removeAll(Collections.singleton(null));
        Preconditions.checkState(whiteCards.size() > 0, "number cards");
        this.cards = Preconditions.checkNotNull(whiteCards, "cards");
    }

    public ITrick getTrick() {
        return trick;
    }

    public Collection<WhiteCard> getWhiteCards() {
        return cards;
    }

    public WhiteCard getWhiteCard() {
        return Iterables.get(cards, 0);
    }
}
