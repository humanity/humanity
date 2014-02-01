package com.ttaylorr.dev.humanity.server.packets;

import java.util.List;

import com.ttaylorr.dev.humanity.server.gameParts.BlackCard;
import com.ttaylorr.dev.humanity.server.gameParts.WhiteCard;

public class Packet01BlackCardSelection extends Packet {
	private WhiteCard whiteCard;
	private List<BlackCard> selection;
}
