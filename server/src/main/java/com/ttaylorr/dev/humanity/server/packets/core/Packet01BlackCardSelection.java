package com.ttaylorr.dev.humanity.server.packets.core;

import com.ttaylorr.dev.humanity.server.cards.core.BlackCard;
import com.ttaylorr.dev.humanity.server.cards.core.WhiteCard;
import com.ttaylorr.dev.humanity.server.packets.Packet;

import java.util.List;

public class Packet01BlackCardSelection extends Packet {
    private WhiteCard whiteCard;
    private List<BlackCard> selection;
}
