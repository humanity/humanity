package net.humanity_game.server.game.state;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.server.game.HumanityGame;
import net.humanity_game.server.game.state.states.*;

public enum GameState {

    PRE_HAND, /*runs prior to the hand starting--the game object doesn't exist until then, so there isn't a true lobby game state, I don't think*/

    ASSIGNING_CZAR,

    SUBMITTING_CARDS,

    PICKING_CARDS,

    COMPLETED; /*alert the players to the victor, and then move onto the pre-hand state.*/

    public interface Requirement {

        public boolean isMet();

        public String toString();
    }

    public static ImmutableList<IGameState> getStates() {
        return states;
    }

    public static IGameState getState(GameState state) {
        return state == null ? null : states.get(state.ordinal()); // this is discouraged but it will always work in this situation.
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

    public static void initStatesList(HumanityGame game) {
        states = ImmutableList.of(new PreHandState(game), new AssigningCzarState(game), new SubmittingCardsState(game), new PickingCardsState(game), new CompletedHandState(game));
    }
}
