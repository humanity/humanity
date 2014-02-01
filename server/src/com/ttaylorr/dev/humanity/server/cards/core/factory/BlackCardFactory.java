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
					cards.add(new BlackCard(parseBlackCardText(t))); // TODO
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

	private String[] parseBlackCardText(String x) {
		throw new NotImplementedException();

	}

}
