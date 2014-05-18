package net.humanity_game.server.game.state.states;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.server.game.HumanityGame;
import net.humanity_game.server.game.state.GameState;
import net.humanity_game.server.game.state.requirements.AlertedPlayersRequirement;
import net.humanity_game.server.game.state.requirements.AlertedScoresRequirement;

/**
 * User: Jack Date: 5/17/2014 Time: 11:48 PM
 */
public class CompletedHandState extends IGameState {

    private ImmutableList<GameState.Requirement> requirements;
    private HumanityGame game;

    public CompletedHandState(HumanityGame game) {
        super(GameState.COMPLETED);
        this.game = Preconditions.checkNotNull(game, "game");
        requirements = ImmutableList.of(new AlertedPlayersRequirement(this, game), new AlertedScoresRequirement(this, game));
    }


    @Override
    public ImmutableList<GameState.Requirement> getRequirements() {
        return requirements;
    }

    public AlertedPlayersRequirement getPlayersRequirement() {
        return (AlertedPlayersRequirement) requirements.get(0);
    }

    public AlertedScoresRequirement getScoresRequirement() {
        return (AlertedScoresRequirement) requirements.get(1);
    }

    @Override
    public void start() {
        // todo send the appropriate packets, calling the previous two methods as we go.
    }
}
