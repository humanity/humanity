package net.humanity_game.server.game.state.requirements;

import com.google.common.base.Preconditions;
import net.humanity_game.server.game.HumanityGame;
import net.humanity_game.server.game.state.GameState;
import net.humanity_game.server.game.state.states.IGameState;

public class SubmittedCardsRequirement implements GameState.Requirement {

    private HumanityGame game;
    private IGameState state;

    public SubmittedCardsRequirement(IGameState state, HumanityGame game) {
        this.game = Preconditions.checkNotNull(game, "game");
        this.state = Preconditions.checkNotNull(state, "state");
    }

    @Override
    public boolean isMet() {
        return false; // todo true if all the players have submitted the proper number of cards.
    }
}
