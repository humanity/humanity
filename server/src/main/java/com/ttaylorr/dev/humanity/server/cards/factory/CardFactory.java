package com.ttaylorr.dev.humanity.server.cards.factory;

import com.google.common.base.Preconditions;
import com.oracle.javafx.jmx.json.JSONDocument;
import com.oracle.javafx.jmx.json.JSONFactory;
import com.ttaylorr.dev.humanity.server.HumanityServer;
import com.ttaylorr.dev.humanity.server.cards.card.HumanityCard;
import com.ttaylorr.dev.humanity.server.cards.deck.HumanityDeck;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class CardFactory<T extends HumanityCard> {

    protected JSONDocument document;
    protected Set<T> cards;
    protected HumanityServer server;

    /** JSON document names for different parameters. Use for reference/parsing. */
    public static final String ID = "id";
    public static final String CARD_TYPE = "cardType";
    public static final String TEXT = "text";
    public static final String ANSWERS = "numAnswers";
    public static final String EXPANSION = "expansion";

    protected CardFactory(File f, HumanityServer server) {
        try {
            this.document = JSONFactory.instance().makeReader(new FileReader(f)).build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.cards = new HashSet<>();
        this.server = Preconditions.checkNotNull(server, "server");
    }

    protected JSONDocument getDocument() {
        return this.document;
    }

    protected List<JSONDocument> getConvertedDocuments() {
        List<JSONDocument> documents = new ArrayList<>();

        for (Object o : this.getDocument().array()) {
            documents.add((JSONDocument) o);
        }

        return documents;
    }

    protected Set<T> getCards() {
        return this.cards;
    }

    protected HumanityServer getServer() {
        return this.server;
    }

    public abstract CardFactory<T> parse();

    public abstract HumanityDeck<T> build();
}
