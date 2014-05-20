package net.humanity_game.server.packets.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import net.humanity_game.server.cards.card.BlackCard;
import net.humanity_game.server.cards.card.WhiteCard;
import net.humanity_game.server.packets.Packet;

import java.util.Collection;
import java.util.Collections;

public class Packet13WhiteCardChoice extends Packet {

    private final BlackCard forCard;
    private final Collection<WhiteCard> cards;

    public Packet13WhiteCardChoice(BlackCard forCard, Collection<WhiteCard> whiteCards) {
        super(null);
        this.forCard = Preconditions.checkNotNull(forCard, "card");
        whiteCards.removeAll(Collections.singleton(null));
        Preconditions.checkState(whiteCards.size() > 0, "number cards");
        this.cards = Preconditions.checkNotNull(whiteCards, "cards");
    }

    public BlackCard getBlackCard() {
        return forCard;
    }

    public Collection<WhiteCard> getWhiteCards() {
        return cards;
    }

    public WhiteCard getWhiteCard() {
        return Iterables.get(cards, 0);
    }


}
