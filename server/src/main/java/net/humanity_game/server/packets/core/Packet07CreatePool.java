package net.humanity_game.server.packets.core;

import com.google.common.base.Preconditions;
import net.humanity_game.server.cards.card.BlackCard;
import net.humanity_game.server.packets.Packet;

import java.util.UUID;

public class Packet07CreatePool extends Packet {

    private final UUID gameId;
    private final BlackCard choice;

    public Packet07CreatePool(UUID uuid, BlackCard choice) {
        super(uuid);
        this.gameId = UUID.randomUUID(); // uh?
        this.choice = Preconditions.checkNotNull(choice, "choice");
    }

    public UUID getGameId() {
        return this.gameId;
    }

    public final BlackCard getChoice() {
        return this.choice;
    }
}
