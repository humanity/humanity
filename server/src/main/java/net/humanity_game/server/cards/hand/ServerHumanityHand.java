package net.humanity_game.server.cards.hand;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.server.cards.card.WhiteCard;
import net.humanity_game.server.client.ClientConnection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServerHumanityHand implements IHumanityHand, Serializable {

    private final ClientConnection owner;
    private final List<WhiteCard> cards;

    public ServerHumanityHand(ClientConnection owner) {
        this.cards = new ArrayList<>();
        this.owner = Preconditions.checkNotNull(owner, "owner");
    }

    /** Doesn't actually send updates to the client */
    @Override
    public boolean releaseCard(WhiteCard card) {
        return this.cards.remove(card);
    }

    /** Doesn't actually send updates to the client */
    public boolean addCard(WhiteCard card) {
        return this.cards.add(card);
    }

    @Override
    public boolean shouldDraw() {
        return this.cards.size() != IHumanityHand.MAX_HAND_SIZE;
    }

    @Override
    public ImmutableList<WhiteCard> getCards() {
        return ImmutableList.copyOf(this.cards);
    }
}
