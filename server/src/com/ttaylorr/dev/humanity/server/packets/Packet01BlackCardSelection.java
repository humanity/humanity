package com.ttaylorr.dev.humanity.server.packets;

import java.util.List;

import com.ttaylorr.dev.humanity.server.cards.core.BlackCard;
import com.ttaylorr.dev.humanity.server.cards.core.WhiteCard;

public class Packet01BlackCardSelection extends Packet {
	private WhiteCard whiteCard;
	private List<BlackCard> selection;
}
