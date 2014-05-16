package net.humanity_game.server.game.state;

import com.google.common.collect.ImmutableList;

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

    private static ImmutableList<IGameState> states;

    static {
        states = ImmutableList.of();
    }


}
