package net.humanity_game.server.game.state.requirements;

import com.google.common.base.Preconditions;
import net.humanity_game.server.client.ClientConnection;
import net.humanity_game.server.game.HumanityGame;
import net.humanity_game.server.game.state.GameState;
import net.humanity_game.server.game.state.states.IGameState;

import java.util.HashSet;

public class AlertedPlayersRequirement implements GameState.Requirement {

    private HashSet<ClientConnection> alertedPlayers;
    private HumanityGame game;
    private IGameState state;

    public AlertedPlayersRequirement(IGameState state, HumanityGame game) {
        this.game = Preconditions.checkNotNull(game, "game");
        this.state = Preconditions.checkNotNull(state, "state");
        alertedPlayers = new HashSet<ClientConnection>();
    }

    @Override
    public boolean isMet() {
        return alertedPlayers.equals(game.getPlayers());
    }

    /**
     * It is expected that while this requirement might be in use, the clients of this class will realize this, cast
     * from a Requirement to this class, and then call this method.
     *
     * @param player
     */
    public void confirmAlert(ClientConnection player) {
        alertedPlayers.add(player);
    }


}
