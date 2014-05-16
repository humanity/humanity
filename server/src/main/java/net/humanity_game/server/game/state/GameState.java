package net.humanity_game.server.game.state;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.server.game.state.states.IGameState;

public enum GameState {

    LOBBY,

    ASSIGNING_CSAR,

    SUBMITTING_CARDS,

    PICKING_CARDS,

    COMPLETED;

    public interface Requirement {

        public boolean isMet();

        public String toString();
    }

    public static ImmutableList<IGameState> getStates() {
        return states;
    }

    public static IGameState getState(GameState state) {
        Preconditions.checkNotNull(state, "state");
        return states.get(state.ordinal()); // this is discouraged but it will always work in this situation.
    }


    private static ImmutableList<IGameState> states;

    static {
        states = ImmutableList.of(); // add all of the state IGameStates to this.
    }


}
