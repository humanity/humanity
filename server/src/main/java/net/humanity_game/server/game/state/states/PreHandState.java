package net.humanity_game.server.game.state.states;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.server.game.HumanityGame;
import net.humanity_game.server.game.state.GameState;
import net.humanity_game.server.game.state.requirements.CardCsarRequirement;
import net.humanity_game.server.game.state.requirements.CardsDealtRequirement;

/**
 * deal cards, assign card csar, move gamestate to ASSIGNING_CSAR,
 *
 * ^^^ ensure that these things have been fulfilled
 */
public class PreHandState extends IGameState {
    private ImmutableList<GameState.Requirement> requirementsList;

    public PreHandState(HumanityGame game) {
        super(GameState.PRE_HAND);
        Preconditions.checkNotNull(game, "game");
        requirementsList = ImmutableList.of(new CardsDealtRequirement(this, game), new CardCsarRequirement(this, game));
    }

    @Override
    public ImmutableList<GameState.Requirement> getRequirements() {
        return requirementsList;
    }

    @Override
    public void start() {
        // todo anything here?
    }


    @Override
    public String toString() {
        return this.getGameState().toString();
    }
}
