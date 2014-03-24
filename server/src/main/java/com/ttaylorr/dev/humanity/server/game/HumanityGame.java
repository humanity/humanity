package com.ttaylorr.dev.humanity.server.game;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.HumanityServer;
import com.ttaylorr.dev.humanity.server.cards.BlackCardDeck;
import com.ttaylorr.dev.humanity.server.cards.WhiteCardDeck;
import com.ttaylorr.dev.humanity.server.cards.factory.BlackCardFactory;
import com.ttaylorr.dev.humanity.server.cards.factory.WhiteCardFactory;

import java.io.File;

public class HumanityGame {

    private final HumanityServer server;

    private final WhiteCardDeck whiteCardDeck;
    private final BlackCardDeck blackCardDeck;

    public HumanityGame(File cardsFile, HumanityServer server) {
        this.server = Preconditions.checkNotNull(server, "server");

        this.whiteCardDeck = new WhiteCardFactory(cardsFile, this.server).parse().build();
        this.blackCardDeck = new BlackCardFactory(cardsFile, this.server).parse().build();
    }

    public WhiteCardDeck getWhiteCardDeck() {
        return this.whiteCardDeck;
    }

    public BlackCardDeck getBlackCardDeck() {
        return this.blackCardDeck;
    }
}
