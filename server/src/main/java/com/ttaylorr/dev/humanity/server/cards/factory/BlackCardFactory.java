package com.ttaylorr.dev.humanity.server.cards.factory;

import com.google.gson.JsonElement;
import com.ttaylorr.dev.humanity.server.HumanityServer;
import com.ttaylorr.dev.humanity.server.cards.card.BlackCard;
import com.ttaylorr.dev.humanity.server.cards.deck.BlackCardDeck;

import java.io.File;
import java.io.FileNotFoundException;

public class BlackCardFactory extends CardFactory<BlackCard> {

    public BlackCardFactory(File f, HumanityServer server) throws FileNotFoundException {
        super(f, server);
    }

    @Override
    public BlackCardFactory parse() {
        for (JsonElement element : this.doc.getAsJsonArray()) {
            if (element.getAsJsonObject().get(CardFactory.CARD_TYPE).getAsCharacter() == 'Q') {
                this.cards.add(this.gson.fromJson(element, BlackCard.class));
            }
        }
        return this;
    }

    @Override
    public BlackCardDeck build() {
        return new BlackCardDeck(this.getCards());
    }
}
