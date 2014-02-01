package com.ttaylorr.dev.humanity.server.cards.hand;

import java.util.Collections;
import java.util.LinkedList;

import com.ttaylorr.dev.humanity.server.cards.core.BlackCard;
import com.ttaylorr.dev.humanity.server.cards.core.Card;
import com.ttaylorr.dev.humanity.server.cards.core.WhiteCard;
import com.ttaylorr.dev.humanity.server.cards.core.factory.BlackCardFactory;
import com.ttaylorr.dev.humanity.server.cards.core.factory.CardFactory;
import com.ttaylorr.dev.humanity.server.cards.core.factory.WhiteCardFactory;

public class Deck<C extends Card> {

	private LinkedList<C> onDeck;
	private final CardFactory<C> factory;

	public Deck(CardFactory<C> factory) {
		onDeck = (LinkedList<C>) Collections.synchronizedList(new LinkedList<C>());
		this.factory = factory;
	}

	public synchronized C drawCard() {
		return onDeck.pop();
	}

	public synchronized void releaseCard(C card) {
		onDeck.offer(card);
	}

	private static Deck<BlackCard> blackDeck;
	private static Deck<WhiteCard> whiteDeck;

	static {
		BlackCardFactory bcf = new BlackCardFactory();
		// TODO add paths to bcf's pullPaths
		WhiteCardFactory wcf = new WhiteCardFactory();
		// TODO add paths to wcf's pullPaths

		blackDeck = new Deck<>(bcf);
		whiteDeck = new Deck<>(wcf);
	}

	public static Deck<BlackCard> getBlackDeck() {
		return blackDeck;
	}

	public static Deck<WhiteCard> getWhiteDeck() {
		return whiteDeck;
	}

}
