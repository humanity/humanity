package net.humanity_game.server.game.state.requirements;

import com.google.common.base.Preconditions;
import net.humanity_game.server.game.HumanityGame;
import net.humanity_game.server.game.state.GameState;
import net.humanity_game.server.game.state.states.IGameState;

/**
 * PickedCards -- has the czar submitted his or her selection and that the selection is valid.
 */
public class PickedCardsRequirement implements GameState.Requirement {

    private HumanityGame game;
    private IGameState state;

    public PickedCardsRequirement(IGameState state, HumanityGame game) {
        this.game = Preconditions.checkNotNull(game, "game");
        this.state = Preconditions.checkNotNull(state, "state");
    }

    @Override
    public boolean isMet() {
        // todo implement this !
        return false;
    }
}
