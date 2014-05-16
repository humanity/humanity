package net.humanity_game.server.game.state.requirements;

import net.humanity_game.server.client.ServerClientManager;
import net.humanity_game.server.game.state.GameState;
import net.humanity_game.server.game.state.states.LobbyState;

public class CardsDealtRequirement implements GameState.Requirement {

    private LobbyState state;

    @Override
    public boolean isMet() {

        return false;
    }
}
