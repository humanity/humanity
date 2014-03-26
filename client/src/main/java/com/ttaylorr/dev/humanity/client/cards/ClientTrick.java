package com.ttaylorr.dev.humanity.client.cards;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.client.game.ClientGame;
import com.ttaylorr.dev.humanity.server.cards.card.BlackCard;
import com.ttaylorr.dev.humanity.server.cards.card.WhiteCard;
import com.ttaylorr.dev.humanity.server.cards.pool.ITrick;
import com.ttaylorr.dev.humanity.server.handlers.Listenable;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientTrick extends ITrick implements Listenable {

    private final BlackCard choiceCard;
    private final List<WhiteCard> submitted;

    private final ClientGame game;
    private final UUID trickID;

    public ClientTrick(UUID trickId, BlackCard choiceCard, ClientGame game) {
        this.game = Preconditions.checkNotNull(game, "game");
        this.trickID = trickId;

        this.choiceCard = Preconditions.checkNotNull(choiceCard, "card");
        this.submitted = new CopyOnWriteArrayList<>();

        this.game.registerHandlers(this);
    }

    @Override
    public BlackCard getChoiceCard() {
        return this.choiceCard;
    }

    @Override
    public List<WhiteCard> getSubmitted() {
        return this.submitted;
    }

    @Override
    public boolean isComplete() {
        return this.submitted.size() >= this.choiceCard.getNumberToFill();
    }
}
