package net.humanity_game.server.game.state.requirements;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.server.client.ClientConnection;
import net.humanity_game.server.client.player.PlayerState;
import net.humanity_game.server.game.HumanityGame;
import net.humanity_game.server.game.state.GameState;
import net.humanity_game.server.game.state.states.IGameState;

/**
 * Verify that there is in fact a (exactly one) player that is the card czar, not that that player is the correct one
 * (yet, at least).
 */
public class CardCzarRequirement implements GameState.Requirement {

    private IGameState state;
    private HumanityGame game;

    public CardCzarRequirement(IGameState state, HumanityGame game) {
        this.state = Preconditions.checkNotNull(state, "state");
        this.game = Preconditions.checkNotNull(game, "game");
    }

    @Override
    public boolean isMet() {
        // it's not convenient to check that the czar has been properly advanced, beyond that there's only one czar, so
        // we'll skip that for now.
        ImmutableList<ClientConnection> players = game.getPlayers();
        int count = 0;
        for (ClientConnection cnn : players) {
            if (cnn.getDefinition().getPlayerState() == PlayerState.CARD_CZAR) {
                count++;
            }
        }
        return count == 1;
    }
}
