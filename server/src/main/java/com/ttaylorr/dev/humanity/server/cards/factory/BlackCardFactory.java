package com.ttaylorr.dev.humanity.server.cards.factory;

import com.oracle.javafx.jmx.json.JSONDocument;
import com.ttaylorr.dev.humanity.server.cards.BlackCard;
import com.ttaylorr.dev.humanity.server.cards.BlackCardDeck;
import com.ttaylorr.dev.humanity.server.cards.HumanityCard;

import java.io.File;
import java.util.LinkedList;

public class BlackCardFactory extends CardFactory<BlackCard> {

    public BlackCardFactory(File f) {
        super(f);
    }

    @Override
    public void parse() {
        for(JSONDocument doc : this.getConvertedDocuments()) {
            if (doc.getString(CardFactory.CARD_TYPE).equals("Q")) {
                String messageRaw = doc.getString(CardFactory.TEXT);
                LinkedList<String> messages = new LinkedList<>();

                // TODO: fixme!
                StringBuilder word = new StringBuilder();
                for(char c : messageRaw.toCharArray()) {
                    if(c == '_') {
                        messages.add(word.toString());
                        word = new StringBuilder();
                        messages.add(null);
                    } else {
                        word.append(c);
                    }
                }

                HumanityCard.Expansion expansion = HumanityCard.Expansion.forString(doc.getString(CardFactory.EXPANSION));

                BlackCard card = new BlackCard(messages, expansion);
                this.getCards().add(card);
            }
        }
    }

    @Override
    public BlackCardDeck build() {
        return new BlackCardDeck(this.getCards());
    }
}
