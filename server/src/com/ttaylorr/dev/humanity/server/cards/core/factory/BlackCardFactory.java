package com.ttaylorr.dev.humanity.server.cards.core.factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Pattern;

import com.ttaylorr.dev.humanity.server.cards.core.BlackCard;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BlackCardFactory extends CardFactory<BlackCard> {
	@Override
	protected void gatherFromFile(String t) {

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(t));
			while (reader.ready()) {
				String p = reader.readLine();
				if (!p.isEmpty()) {
					cards.add(new BlackCard(parseBlackCardText(t)));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * TODO test
	 * 
	 * @param x
	 * @return
	 */
	private String[] parseBlackCardText(String x) {
		String[] parts = x.split("\\w");
		for (String p : parts) {
			if (p.equals("%blank")) {
				p = null;
			}
		}
		return parts;
	}

}
