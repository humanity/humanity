package net.humanity_game.server.packets.core;

import com.google.common.base.Preconditions;
import net.humanity_game.server.game.state.GameState;
import net.humanity_game.server.packets.Packet;

/**
 * Used to inform the Client of a GameState change.
 * S->C
 */
public class Packet08GameChangeState extends Packet {

    private final GameState from;
    private final GameState to;

    public Packet08GameChangeState(GameState from, GameState to) {
        super(null);
        this.from = Preconditions.checkNotNull(from, "from");
        this.to = Preconditions.checkNotNull(to, "to");
    }

    public GameState getFrom() {
        return this.from;
    }

    public GameState getTo() {
        return this.to;
    }
}
