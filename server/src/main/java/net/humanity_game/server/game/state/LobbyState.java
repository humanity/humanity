package net.humanity_game.server.game.state;

import com.google.common.collect.ImmutableList;

/**
 * deal cards, assign card csar, move gamestate to ASSIGNING_CSAR,
 *
 * ^^^ ensure that these things have been fulfilled
 */
public class LobbyState extends IGameState {

    public LobbyState() {
        super(GameState.LOBBY);
        requirementsList = ImmutableList.of();
    }

    ImmutableList<GameState.Requirement> requirementsList;


    @Override
    public ImmutableList<GameState.Requirement> getRequirements() {
        return requirementsList;
    }

    @Override
    public String toString() {
        return this.getGameState().toString();
    }
}
