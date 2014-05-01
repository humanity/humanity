package net.humanity_game.server.cards.factory;

import com.google.gson.*;
import net.humanity_game.server.HumanityServer;
import net.humanity_game.server.cards.card.BlackCard;
import net.humanity_game.server.cards.card.HumanityCard;
import net.humanity_game.server.cards.card.WhiteCard;
import net.humanity_game.server.cards.deck.HumanityDeck;
import net.humanity_game.server.cards.gson.BlackCardTypeAdapter;
import net.humanity_game.server.cards.gson.WhiteCardTypeAdapter;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public abstract class CardFactory<T extends HumanityCard> {

    protected Gson gson;
    protected JsonElement doc;
    protected Set<T> cards;
    protected HumanityServer server;

    /** JSON document names for different parameters. Use for reference/parsing. */
    public static final String ID = "id";
    public static final String CARD_TYPE = "cardType";
    public static final String TEXT = "text";
    public static final String ANSWERS = "numAnswers";
    public static final String EXPANSION = "expansion";

    protected CardFactory(File f, HumanityServer server) throws FileNotFoundException {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(WhiteCard.class, new WhiteCardTypeAdapter())
                .registerTypeAdapter(BlackCard.class, new BlackCardTypeAdapter())
                .create();

        this.cards = new HashSet<>();
        this.doc = new JsonParser().parse(new BufferedReader(new FileReader(f)));
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
