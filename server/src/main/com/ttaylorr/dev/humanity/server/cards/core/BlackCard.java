package com.ttaylorr.dev.humanity.server.cards.core;

import java.util.List;

/**
 * @author Jack
 * 
 */
public class BlackCard extends Card {

	private int blanks;

	public BlackCard(String... components) {
		super(joinOnComponents(components));
		for (String x : components) {
			if (x == null)
				blanks++;
		}
	}

	private static String joinOnComponents(String... components) {
		StringBuilder builder = new StringBuilder();
		for (String x : components) {
			builder.append(x);
		}
		return builder.toString();
	}

	public BlackCard(List<String> components) {
		this(components.toArray(new String[0]));
	}

	/**
	 * 
	 * @return Returns the number of WhiteCards that should be submitted in response to this BlackCard.
	 */
	public int getBlanks() {
		return blanks;
	}

}
