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
        //todo check that the czar is the correct person
        ImmutableList<ClientConnection> players = game.getPlayers();
        int cscount = 0;
        for (ClientConnection cnn : players) {
            if (cnn.getDefinition().getPlayerState() == PlayerState.CARD_CZAR) {
                cscount++;
            }
        }
        return cscount == 1;
    }
}
