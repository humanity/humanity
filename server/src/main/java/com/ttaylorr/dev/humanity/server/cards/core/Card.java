package com.ttaylorr.dev.humanity.server.cards.core;

import com.ttaylorr.dev.humanity.server.packets.Packet;
import com.ttaylorr.dev.humanity.server.player.Player;

public abstract class Card extends Packet {
	private final String text;
	private Player owner;

	Card(String nText) {
		text = nText;
	}
}
