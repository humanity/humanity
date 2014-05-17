package net.humanity_game.server.game.state.states;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.server.game.state.GameState;

public abstract class IGameState {

    public boolean canAdvanceState() {
        ImmutableList<GameState.Requirement> reqs = getRequirements();
        for (GameState.Requirement requirement : reqs) {
            if (!requirement.isMet()) return false;
        }
        return true;
    }

    public abstract ImmutableList<GameState.Requirement> getRequirements();

    /**
     * Begin performing whatever actions are called for by this game state.
     */
    public abstract void start();

    public abstract String toString();

    private final GameState gameState;

    protected IGameState(GameState gs) {
        this.gameState = Preconditions.checkNotNull(gs, "gamestate");
    }

    public final GameState getGameState() {
        return gameState;
    }


}
