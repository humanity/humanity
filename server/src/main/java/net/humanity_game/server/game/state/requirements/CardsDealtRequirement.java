package net.humanity_game.server.game.state.requirements;

import net.humanity_game.server.Bootstrap;
import net.humanity_game.server.game.HumanityGame;
import net.humanity_game.server.game.state.GameState;
import net.humanity_game.server.game.state.LobbyState;

public class CardsDealtRequirement implements GameState.Requirement {

    private LobbyState state;

    @Override
    public boolean isMet() {
        // get the current humanity game and make sure the cards have been dealt to those players that need them (all of them, probably).
        return false;
    }
}
