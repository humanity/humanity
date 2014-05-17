package net.humanity_game.server.game.state.requirements;

import com.google.common.base.Preconditions;
import net.humanity_game.server.client.ClientConnection;
import net.humanity_game.server.game.HumanityGame;
import net.humanity_game.server.game.state.GameState;
import net.humanity_game.server.game.state.states.LobbyState;

public class CardsDealtRequirement implements GameState.Requirement {

    private LobbyState state;
    private HumanityGame game;

    public CardsDealtRequirement(LobbyState state, HumanityGame game) {
        this.state = Preconditions.checkNotNull(state, "state");
        this.game = Preconditions.checkNotNull(game, "game");
    }

    @Override
    public boolean isMet() {
        for (ClientConnection conn : game.getPlayers()) {
            // todo with the hand system, we'll check that each player has the right number of players.
        }
        return false;
    }
}
