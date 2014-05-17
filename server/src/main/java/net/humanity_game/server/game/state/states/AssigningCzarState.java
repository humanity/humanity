package net.humanity_game.server.game.state.states;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.server.game.HumanityGame;
import net.humanity_game.server.game.state.GameState;
import net.humanity_game.server.game.state.requirements.CardCzarRequirement;

/**
 * User: Jack Date: 5/16/2014 Time: 11:11 PM
 */
public class AssigningCzarState extends IGameState {
    private ImmutableList<GameState.Requirement> requirementsList;


    public AssigningCzarState(HumanityGame game) {
        super(GameState.ASSIGNING_CZAR);
        Preconditions.checkNotNull(game, "game");
        requirementsList = ImmutableList.of((GameState.Requirement) new CardCzarRequirement(this, game));
    }

    @Override
    public ImmutableList<GameState.Requirement> getRequirements() {
        return requirementsList;
    }

    @Override
    public void start() {
        // todo choose the czar, send the packets, etc.
    }


    @Override
    public String toString() {
        return this.getGameState().toString();
    }

}
