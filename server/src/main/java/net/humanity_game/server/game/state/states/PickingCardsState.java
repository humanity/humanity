package net.humanity_game.server.game.state.states;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.server.game.HumanityGame;
import net.humanity_game.server.game.state.GameState;
import net.humanity_game.server.game.state.requirements.PickedCardsRequirement;

/**
 * the czar has sent back the picked-card packet, and the card has been verified as valid
 */
public class PickingCardsState extends IGameState {

    private HumanityGame game;
    private ImmutableList<GameState.Requirement> requirements;

    public PickingCardsState(HumanityGame game) {
        super(GameState.PICKING_CARDS);
        this.game = Preconditions.checkNotNull(game, "game");
        requirements = ImmutableList.of((GameState.Requirement) new PickedCardsRequirement(this, game));
    }


    @Override
    public ImmutableList<GameState.Requirement> getRequirements() {
        return requirements;
    }

    @Override
    public void start() {
        // todo send out the packets that tell of the current black card. Wait for the responses.

    }

}
