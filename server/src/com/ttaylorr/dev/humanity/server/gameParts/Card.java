package com.ttaylorr.dev.humanity.server.gameParts;

import com.ttaylorr.dev.humanity.server.packets.Packet;

public abstract class Card extends Packet {
	private final String text;
	private Player owner;

	Card(String nText) {
		text = nText;
	}
}
