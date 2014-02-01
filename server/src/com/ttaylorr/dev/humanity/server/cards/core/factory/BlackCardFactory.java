package com.ttaylorr.dev.humanity.server.cards.core.factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.ttaylorr.dev.humanity.server.cards.core.BlackCard;

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
	 * Read each line from a file, specified by path <code>x</code>, and return an array of them.
	 * @param path path of file to be parsed
	 * @return
	 */
	private String[] parseBlackCardText(String path) {
		String[] parts = path.split("\\w");
		for (String p : parts) {
			if (p.equals("%blank")) {
				p = null;
			}
		}
		return parts;
	}

}
