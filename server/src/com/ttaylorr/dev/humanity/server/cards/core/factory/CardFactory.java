package com.ttaylorr.dev.humanity.server.cards.core.factory;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.ttaylorr.dev.humanity.server.cards.core.Card;

public abstract class CardFactory<C extends Card> {

	protected LinkedList<C> cards;
	private List<String> pullPaths;

	public CardFactory() {
		cards = new LinkedList<>();

	}

	public void addPath(String path) {
		if (!pullPaths.contains(path))
			pullPaths.add(path);

	}

	public Iterator<C> iter() {
		return cards.iterator();
	}

	/**
	 * Read from the pullPaths, and then populate cards, shuffling as you go.
	 * 
	 * The format for these files will be [file] [file] [file], with an OS-appropriate line-ending.
	 */
	void buildIndex() {
		for (String s : pullPaths) {
			gatherFromFile(s);
		}

		Collections.shuffle(cards);
	}

	protected abstract void gatherFromFile(String t);

}
