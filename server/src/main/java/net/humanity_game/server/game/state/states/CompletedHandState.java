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

    private AlertedPlayersRequirement playersRequirement;
    private AlertedScoresRequirement scoresRequirement;

    private HumanityGame game;

    public CompletedHandState(HumanityGame game) {
        super(GameState.COMPLETED);
        this.game = Preconditions.checkNotNull(game, "game");
        playersRequirement = new AlertedPlayersRequirement(this, game);
        scoresRequirement = new AlertedScoresRequirement(this, game);
    }


    @Override
    public ImmutableList<GameState.Requirement> getRequirements() {
        return ImmutableList.of(playersRequirement, scoresRequirement);
    }

    public AlertedPlayersRequirement getPlayersRequirement() {
        return playersRequirement;
    }

    public AlertedScoresRequirement getScoresRequirement() {
        return scoresRequirement;
    }

    @Override
    public void start() {
        // todo send the appropriate packets, calling the previous two methods as we go.
    }
}
