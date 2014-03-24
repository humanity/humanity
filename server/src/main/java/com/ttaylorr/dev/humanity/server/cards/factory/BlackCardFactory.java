package com.ttaylorr.dev.humanity.server.cards.factory;

import com.oracle.javafx.jmx.json.JSONDocument;
import com.ttaylorr.dev.humanity.server.HumanityServer;
import com.ttaylorr.dev.humanity.server.cards.BlackCard;
import com.ttaylorr.dev.humanity.server.cards.BlackCardDeck;
import com.ttaylorr.dev.humanity.server.cards.HumanityCard;

import java.io.File;
import java.util.LinkedList;

public class BlackCardFactory extends CardFactory<BlackCard> {

    public BlackCardFactory(File f, HumanityServer server) {
        super(f, server);
    }

    @Override
    public BlackCardFactory parse() {
        for(JSONDocument doc : this.getConvertedDocuments()) {
            if (doc.getString(CardFactory.CARD_TYPE).equals("Q")) {
                String messageRaw = doc.getString(CardFactory.TEXT);
                LinkedList<String> messages = new LinkedList<>();

                // TODO: fixme!
                StringBuilder word = new StringBuilder();
                for(char c : messageRaw.toCharArray()) {
                    if(c == '_') {
                        if(word.toString().length() > 0) {
                            messages.add(word.toString());
                        }
                        word = new StringBuilder();
                        messages.add(null);
                    } else {
                        word.append(c);
                    }
                }
                if(word.toString().length() > 0) {
                    messages.add(word.toString());
                }

                HumanityCard.Expansion expansion = HumanityCard.Expansion.forString(doc.getString(CardFactory.EXPANSION));

                BlackCard card = new BlackCard(messages, expansion);
                this.getCards().add(card);
                this.getServer().getLogger().info("Added black card: " + card.toString());
            }
        }
        return this;
    }

    @Override
    public BlackCardDeck build() {
        return new BlackCardDeck(this.getCards());
    }
}
