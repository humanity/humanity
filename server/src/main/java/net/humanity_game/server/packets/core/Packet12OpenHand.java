package net.humanity_game.server.packets.core;

import com.google.common.base.Preconditions;
import net.humanity_game.server.cards.card.BlackCard;
import net.humanity_game.server.cards.card.WhiteCard;
import net.humanity_game.server.packets.Packet;

import java.util.Collection;
import java.util.UUID;

/**
 * S->C
 */
public class Packet12OpenHand extends Packet {
    private final BlackCard blackCard;
    private final UUID cardCzar;

    public Packet12OpenHand(BlackCard blackCard, UUID cardCzar) {
        super(null);
        this.cardCzar = Preconditions.checkNotNull(cardCzar, "czar");
        this.blackCard = Preconditions.checkNotNull(blackCard, "black card");
    }

    public BlackCard getBlackCard() {
        return blackCard;
    }

    public UUID getCardCzar() {
        return cardCzar;
    }

    public Packet constructResponse(Collection<WhiteCard> whiteCards) {
        Packet13WhiteCardChoice ret = new Packet13WhiteCardChoice(blackCard, whiteCards);
        return ret;
    }

}
