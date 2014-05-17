package net.humanity_game.server.game.state;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.server.game.HumanityGame;
import net.humanity_game.server.game.state.states.AssigningCzarState;
import net.humanity_game.server.game.state.states.IGameState;
import net.humanity_game.server.game.state.states.PreHandState;

public enum GameState {

    PRE_HAND, /*runs proior to the hand starting--the game object doesn't exist until then, so there isn't a true lobby game state, I don't think*/

    ASSIGNING_CZAR,

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

    public static IGameState getNext(IGameState current) {
        Preconditions.checkNotNull(current, "current state");
        if (current.getGameState().ordinal() + 1 >= GameState.values().length) { // I'm sorry, Code gods.
            return getState(GameState.values()[0]);
        } else {
            return getState(GameState.values()[current.getGameState().ordinal() + 1]);
        }
    }

    private static ImmutableList<IGameState> states;

    public void initStatesList(HumanityGame game) {
        states = ImmutableList.of(new PreHandState(game), new AssigningCzarState(game));
    }


}
