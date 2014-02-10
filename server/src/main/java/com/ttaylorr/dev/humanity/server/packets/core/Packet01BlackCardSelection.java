package com.ttaylorr.dev.humanity.server.packets.core;

import com.ttaylorr.dev.humanity.server.Client;
import com.ttaylorr.dev.humanity.server.cards.core.BlackCard;
import com.ttaylorr.dev.humanity.server.cards.core.WhiteCard;
import com.ttaylorr.dev.humanity.server.packets.Packet;

import java.util.List;

public class Packet01BlackCardSelection extends Packet {

    public Packet01BlackCardSelection(Client client) {
        super(client);
    }

    public void setWhiteCard(WhiteCard card) {
        this.whiteCard = card;
    }

    public void addBlackCard(BlackCard card) {
        selection.add(card);
    }


    private WhiteCard whiteCard;
    private List<BlackCard> selection;
}
