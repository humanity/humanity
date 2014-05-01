package net.humanity_game.client.client.player;

import net.humanity_game.server.client.player.PlayerState;
import net.humanity_game.server.client.player.definition.IPlayerDefinition;

public abstract class ClientPlayerDefinition implements IPlayerDefinition {

    protected final String name;
    protected int score;
    protected PlayerState state;

    protected ClientPlayerDefinition(String name) {
        this.name = name;
        this.score = 0;
        this.state = PlayerState.OBSERVING;
    }

    @Override
    public PlayerState getPlayerState() {
        return state;
    }
}
