package com.ttaylorr.dev.humanity.server.packets.core;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.cards.card.BlackCard;
import com.ttaylorr.dev.humanity.server.packets.Packet;

import java.util.UUID;

public class Packet07CreatePool extends Packet {

    private final UUID gameId;
    private final BlackCard choice;

    public Packet07CreatePool(BlackCard choice) {
        this.gameId = UUID.randomUUID();
        this.choice = Preconditions.checkNotNull(choice, "choice");
    }

    public UUID getGameId() {
        return this.gameId;
    }

    public final BlackCard getChoice() {
        return this.choice;
    }
}
