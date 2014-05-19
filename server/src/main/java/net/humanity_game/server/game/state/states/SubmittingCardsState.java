package net.humanity_game.server.game.state.states;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.server.game.HumanityGame;
import net.humanity_game.server.game.state.GameState;
import net.humanity_game.server.game.state.requirements.SubmittedCardsRequirement;

/**
 * the White Cards have all been submitted
 *
 * ^^ensure completion
 */
public class SubmittingCardsState extends IGameState {

    public SubmittingCardsState(HumanityGame game) {
        super(GameState.SUBMITTING_CARDS);
        Preconditions.checkNotNull(game, "game");
        requirements = ImmutableList.of((GameState.Requirement) new SubmittedCardsRequirement(this, game));
    }

    private ImmutableList<GameState.Requirement> requirements;


    @Override
    public ImmutableList<GameState.Requirement> getRequirements() {
        return requirements;
    }

    @Override
    public void start() {
        // todo send out the packets containing the BlackCard's info--begin waiting for card selection packets.
    }

    @Override
    public String toString() {
        return null;
    }
}
