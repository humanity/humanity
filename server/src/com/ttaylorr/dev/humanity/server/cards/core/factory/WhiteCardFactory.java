package com.ttaylorr.dev.humanity.server.cards.core.factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.ttaylorr.dev.humanity.server.cards.core.WhiteCard;

public class WhiteCardFactory extends CardFactory<WhiteCard> {

	public WhiteCardFactory() {
	}

	@Override
	protected void gatherFromFile(String t) {

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(t));
			while (reader.ready()) {
				String p = reader.readLine();
				if (!p.isEmpty()) {
					cards.add(new WhiteCard(t));
				}
			}
		} catch (IOException e) {
			// TODO a user-friendly error reporting system would be good here.
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
